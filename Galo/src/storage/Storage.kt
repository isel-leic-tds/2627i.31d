package storage

/**
 * Interface for a storage system that supports basic CRUD operations.
 * Used for education proposable in TDS (Técnicas de Desenvolvimento de Software).
 * @param Key the type of the key used to identify entries in the storage.
 * @param Data the type of the data stored in the storage.
 */
interface Storage<Key,Data> {
    /**
     * Creates a new entry in the storage.
     * @param key the key of the entry.
     * @param data the data to store.
     * @throws IllegalStateException if the key already exists.
     */
    fun create(key: Key, data: Data)
    /**
     * Reads an entry from the storage.
     * @param key the key of the entry.
     * @return the data stored or null if the key does not exist.
     */
    fun read(key: Key): Data?
    /**
     * Updates an entry in the storage.
     * @param key the key of the entry.
     * @param data the new data to store.
     * @throws IllegalStateException if the key does not exist.
     */
    fun update(key: Key, data: Data)
    /**
     * Deletes an entry from the storage.
     * @param key the key of the entry.
     * @throws IllegalStateException if the key does not exist.
     */
    fun delete(key: Key)
}
