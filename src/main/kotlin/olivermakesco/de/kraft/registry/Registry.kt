package olivermakesco.de.kraft.registry

open class Registry<ID, T> {
    private val registry = HashMap<ID, T>()

    operator fun set(id: ID, obj: T) {
        if (registry[id] != null) error("Cannot overwrite registry")
        registry[id] = obj
    }

    operator fun get(id: ID): T? {
        return registry[id]
    }
}

open class IdentifiableRegistry<T> : Registry<Identifier, T>()
open class IntegerRegistry<T> : Registry<Int, T>()
