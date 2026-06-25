package com.sapan.ekart.analytics

import com.sapan.ekart.analytics.data.local.InteractionDao
import com.sapan.ekart.analytics.data.repository.AnalyticsRepositoryImpl
import com.sapan.ekart.core.analytics.domain.model.Interaction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AnalyticsRepositoryTest {

    private lateinit var dao: InteractionDao
    private lateinit var repository: AnalyticsRepositoryImpl

    @Before
    fun setup() {
        dao = object : InteractionDao {
            private val storage = mutableListOf<com.sapan.ekart.analytics.data.local.entity.InteractionEntity>()

            override suspend fun insertInteraction(interaction: com.sapan.ekart.analytics.data.local.entity.InteractionEntity) {
                storage.add(interaction.copy(id = storage.size + 1))
            }

            override suspend fun getAllInteractions(): List<com.sapan.ekart.analytics.data.local.entity.InteractionEntity> {
                return storage.toList()
            }

            override suspend fun clearInteractions() {
                storage.clear()
            }
        }
        repository = AnalyticsRepositoryImpl(dao)
    }

    @Test
    fun `logInteraction persists interaction and drain returns same payload`() = runTest {
        val interaction = Interaction(
            type = "Join Pool",
            timestamp = 123456789L,
            details = "User clicked join"
        )

        repository.logInteraction(interaction)
        val drained = repository.drainInteractions()

        assertEquals(1, drained.size)
        assertEquals("Join Pool", drained.first().type)
        assertEquals("User clicked join", drained.first().details)
    }

    @Test
    fun `clearInteractions empties stored payload`() = runTest {
        repository.logInteraction(
            Interaction(
                type = "Shared Link",
                timestamp = 123456789L,
                details = "User tapped share"
            )
        )
        repository.clearInteractions()
        assertEquals(0, repository.drainInteractions().size)
    }
}
