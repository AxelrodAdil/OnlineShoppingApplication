package kz.axelrod.cryptofun.data.model.products

data class DepthResponse(
    val lastUpdateId: Long,
    val bids: List<List<String>>,
    val asks: List<List<String>>
) {
    fun getParsedBids(): List<OrderBookEntry> =
        bids.map { OrderBookEntry(it[0].toDouble(), it[1].toDouble()) }

    fun getParsedAsks(): List<OrderBookEntry> =
        asks.map { OrderBookEntry(it[0].toDouble(), it[1].toDouble()) }
}

data class OrderBookEntry(
    val price: Double,
    val quantity: Double
)
