package model

@JvmInline
value class Name(val value: String) {
    init {
        require(value.matches(Regex("[a-zA-Z0-9_]+"))) { "Invalid name $value" }
    }
    override fun toString(): String = value
}