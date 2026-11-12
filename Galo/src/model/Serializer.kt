package model

import storage.Serializer

object GameStateSerializer: Serializer<GameState> {
    override fun serialize(data: GameState) = when (data) {
        is Run -> "Run:${data.turn}"
        is Win -> "Win:${data.winner}"
        Draw -> "Draw:"
    }
    override fun deserialize(text: String): GameState =
        text.split(':').let { [type, player] ->
            when (type) {
                "Run" -> Run(player.toPlayer())
                "Win" -> Win(player.toPlayer())
                "Draw" -> Draw
                else -> error("Unknown game state: $type")
            }
        }
}

object GameSerializer: Serializer<Game> {
    override fun serialize(data: Game): String = buildString {
        appendLine(data.first)
        appendLine(data.board.entries.joinToString(" ") { [pos, player] ->
            "${pos.index}:$player"
        })
        appendLine(GameStateSerializer.serialize(data.state))
        appendLine(data.score.entries.joinToString(" ") { [player, score] ->
            "$player=$score"
        })
    }
    override fun deserialize(text: String): Game =
        text.split('\n').let { [l1, l2, l3, l4] -> Game(
            first = l1.toPlayer(),
            board = if (l2.isEmpty()) emptyMap()
                    else l2.split(' ')
                        .map{ it.split(':') }
                        .associate { [index, player] ->
                            Position(index.toInt()) to player.toPlayer()
                        },
            state = GameStateSerializer.deserialize(l3),
            score = l4.split(' ')
                .map{ it.split('=') }
                .associate { [player, score] ->
                    player.toPlayer() to score.toInt()
                }
        ) }
}