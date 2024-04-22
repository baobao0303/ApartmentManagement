package com.luaga.apartmentapp.data
data class Apartment(
    val apartmentNumber: String,
    val floor: Int,
    val ownerId: Int,
    val numBedrooms: Int,
    val numBathrooms: Int,
    val areaSqft: Int,
    val rent: Int,
    val internetFee: Int,
    val garbageFee: Int,
    val status: String,
    val waterUsage: WaterUsage?,
    val furniture: Furniture,
    val services: List<Service>
)

data class WaterUsage(
    val currentUsage: Int,
    val maxUsage: Int
)

data class Furniture(
    val beds: FurnitureItem,
    val sofas: FurnitureItem,
    val tables: FurnitureItem,
    val chairs: FurnitureItem,
    val appliances: Appliances
)

data class FurnitureItem(
    val quantity: Int,
    val damaged: Int
)

data class Appliances(
    val quantity: Int,
    val damaged: Int,
    val items: List<String>
)

data class Service(
    val name: String,
    val description: String,
    val status: String
)

data class Tenant(
    val name: String,
    val contact: String,
    val phone: String,
    val identityCardNumber: String,
    val moveInDate: String,
    val leaseExpiryDate: String
)
object DummyApartments {
    val apartments = listOf(
        Apartment(
            apartmentNumber = "A101",
            floor = 1,
            ownerId = 1,
            numBedrooms = 2,
            numBathrooms = 1,
            areaSqft = 1000,
            rent = 1500,
            internetFee = 0,
            garbageFee = 0,
            status = "available",
            waterUsage = null,
            furniture = Furniture(
                beds = FurnitureItem(2, 0),
                sofas = FurnitureItem(1, 1),
                tables = FurnitureItem(1, 0),
                chairs = FurnitureItem(4, 0),
                appliances = Appliances(3, 0, listOf("refrigerator", "microwave", "stove"))
            ),
            services = listOf(
                Service("Swimming Pool", "Outdoor swimming pool open from 8am to 8pm.", "available"),
                Service("Gym", "Fully equipped gym available 24/7.", "unavailable")
            )
        ),
        Apartment(
            apartmentNumber = "B202",
            floor = 2,
            ownerId = 1,
            numBedrooms = 3,
            numBathrooms = 2,
            areaSqft = 1200,
            rent = 1800,
            internetFee = 50,
            garbageFee = 20,
            status = "occupied",
            waterUsage = WaterUsage(30, 50),
            furniture = Furniture(
                beds = FurnitureItem(3, 0),
                sofas = FurnitureItem(1, 0),
                tables = FurnitureItem(2, 0),
                chairs = FurnitureItem(6, 0),
                appliances = Appliances(4, 1, listOf("refrigerator", "microwave", "stove", "dishwasher"))
            ),
            services = listOf(
                Service("Swimming Pool", "Outdoor swimming pool open from 8am to 8pm.", "available"),
                Service("Gym", "Fully equipped gym available 24/7.", "unavailable"),
                Service("Laundry", "Coin-operated laundry facilities available in the building.", "available"),
                Service("Parking", "Reserved parking spot available for each apartment.", "available")
            )
        ),
        Apartment(
            apartmentNumber = "C303",
            floor = 3,
            ownerId = 2,
            numBedrooms = 1,
            numBathrooms = 1,
            areaSqft = 800,
            rent = 1200,
            internetFee = 40,
            garbageFee = 15,
            status = "available",
            waterUsage = null,
            furniture = Furniture(
                beds = FurnitureItem(1, 0),
                sofas = FurnitureItem(1, 0),
                tables = FurnitureItem(1, 0),
                chairs = FurnitureItem(2, 0),
                appliances = Appliances(2, 0, listOf("refrigerator", "microwave"))
            ),
            services = listOf(
                Service("Swimming Pool", "Outdoor swimming pool open from 8am to 8pm.", "available"),
                Service("Gym", "Fully equipped gym available 24/7.", "unavailable")
            )
        )
    )
}
