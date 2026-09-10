const val DAYS_NOVEMBER = 30 // Memória global (estática)

fun main() {
    val message =   // Memória stack (automática)
        "Hello, TDS" // Memória heap (dinâmica)

    println("Message = $message")

    println("November has $DAYS_NOVEMBER days")
    println("December has ${DAYS_NOVEMBER + 1} days")
}

/* Tipos em Kotlin:
- Int: números inteiros (ex: 1, 2, 3)
- Long: números inteiros grandes (ex: 1000000000L, 2000000000L)
- Double: números de ponto flutuante (ex: 1.0, 2.5, 3.14)
- Float: números de ponto flutuante menos precisos (ex: 1.0f, 2.5f, 3.14f)
- String: sequências de caracteres (ex: "Hello", "Kotlin")
- Boolean: valores lógicos (ex: true, false)
- Char: um único caractere (ex: 'a', 'b', 'c')

Outros tipos para valores inteiros:
- Byte: 8 bits, valores de -128 a 127
- UByte: 8 bits, valores de 0 a 255
- Short: 16 bits, valores de -32.768 a 32.767
- UShort: 16 bits, valores de 0 a 65.535
- UInt: 32 bits, valores de 0 a 4.294.967.295
- ULong: 64 bits, valores de 0 a 18.446.744.073.709.551.615
 */
