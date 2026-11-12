import storage.*
import kotlin.math.PI
import kotlin.test.*

class TextFileStorageTest {
    private val serializer = object : Serializer<Double> {
        override fun serialize(data: Double): String = data.toString()
        override fun deserialize(text: String): Double = text.toDouble()
    }
    private val storage = TextFileStorage<String, Double>(
        baseFolderName = "BaseDir", serializer
    )
    @Test fun `CRUD operations`() {
        val key = "PI"
        // Create
        storage.create(key, PI)
        assertEquals(PI, storage.read(key))
        assertFailsWith<IllegalStateException> { storage.create(key,0.0) }
        // Update
        val updatedData = PI-3.0
        storage.update(key, updatedData)
        assertEquals(updatedData, storage.read(key))
        // Delete
        storage.delete(key)
        assertNull(storage.read(key))
        assertFailsWith<IllegalStateException> { storage.delete(key) }
        assertFailsWith<IllegalStateException> { storage.update(key,0.0) }
    }
}
