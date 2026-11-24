package model

@JvmInline
value class Name(val value: String) {
    init {
        require(value.isValidName()) { "Invalid name $value" }
    }
    override fun toString(): String = value
}

fun String.isValidName() = matches(Regex("[a-zA-Z0-9_]+"))