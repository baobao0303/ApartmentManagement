package com.luaga.apartmentapp.data
data class Owner(
    val id: String,
    val name: String,
    val contact: String,
    val phone: String,
    val address: String
)

data class FurnitureItem(
    val quantity: Int,
    val damaged: Int
)

data class Appliance(
    val quantity: Int,
    val damaged: Int,
    val items: List<String>
)

data class Furniture(
    val beds: FurnitureItem,
    val sofas: FurnitureItem,
    val tables: FurnitureItem,
    val chairs: FurnitureItem,
    val appliances: Appliance
)

data class Service(
    val name: String,
    val description: String,
    val status: String
)

data class Tenant(
    val name: String,
    val gender: String,
    val contact: String,
    val phone: String,
    val identityCardNumber: String,
    val moveInDate: String,
    val leaseExpiryDate: String
)

data class Apartment(
    val id: String,
    val apartmentNumber: String,
    val floor: Int,
    val numBedrooms: Int,
    val numBathrooms: Int,
    val areaSqft: Int,
    val rent: Int,
    val internetFee: Int,
    val garbageFee: Int,
    val status: String,
    val waterUsage: Any?,
    val furniture: Furniture,
    val tenant: Tenant?,
    val services: List<Service>
)
object DummyApartments {
    val owner = Owner(
        id = "1",
        name = "Nguyễn Văn A",
        contact = "nguyenvana@example.com",
        phone = "123-456-7890",
        address = "123 ABC Street, City, Country"
    )

    val apartmentsList = listOf(
        Apartment(
            id = "1",
            apartmentNumber = "A101",
            floor = 1,
            numBedrooms = 2,
            numBathrooms = 1,
            areaSqft = 1000,
            rent = 1500,
            internetFee = 0,
            garbageFee = 0,
            status = "occupied",
            waterUsage = null,
            furniture = Furniture(
                beds = FurnitureItem(quantity = 2, damaged = 0),
                sofas = FurnitureItem(quantity = 1, damaged = 1),
                tables = FurnitureItem(quantity = 1, damaged = 0),
                chairs = FurnitureItem(quantity = 4, damaged = 0),
                appliances = Appliance(
                    quantity = 3,
                    damaged = 0,
                    items = listOf("refrigerator", "microwave", "stove")
                )
            ),
            tenant = Tenant(
                name = "Jane Doe",
                gender = "Female",
                contact = "jane.doe@example.com",
                phone = "987-654-3210",
                identityCardNumber = "DEF654321",
                moveInDate = "2023-06-01",
                leaseExpiryDate = "2024-05-31"
            ),
            services = listOf(
                Service(
                    name = "Swimming Pool",
                    description = "Hồ bơi ngoài trời mở cửa từ 8 giờ sáng đến 8 giờ tối.",
                    status = "available"
                ),
                Service(
                    name = "Gym",
                    description = "Phòng gym đầy đủ trang thiết bị sẵn có 24/7.",
                    status = "unavailable"
                )
            )
        ),
        Apartment(
            id = "2",
            apartmentNumber = "B202",
            floor = 2,
            numBedrooms = 3,
            numBathrooms = 2,
            areaSqft = 1200,
            rent = 1800,
            internetFee = 50,
            garbageFee = 20,
            status = "occupied",
            waterUsage = mapOf("current_usage" to 30, "max_usage" to 50),
            furniture = Furniture(
                beds = FurnitureItem(quantity = 3, damaged = 0),
                sofas = FurnitureItem(quantity = 1, damaged = 0),
                tables = FurnitureItem(quantity = 2, damaged = 0),
                chairs = FurnitureItem(quantity = 6, damaged = 0),
                appliances = Appliance(
                    quantity = 4,
                    damaged = 1,
                    items = listOf("refrigerator", "microwave", "stove", "dishwasher")
                )
            ),
            tenant = Tenant(
                name = "John Doe",
                gender = "Female",
                contact = "john.doe@example.com",
                phone = "123-456-7890",
                identityCardNumber = "ABC123456",
                moveInDate = "2023-05-15",
                leaseExpiryDate = "2024-05-14"
            ),
            services = listOf(
                Service(
                    name = "Swimming Pool",
                    description = "Hồ bơi ngoài trời mở cửa từ 8 giờ sáng đến 8 giờ tối.",
                    status = "available"
                ),
                Service(
                    name = "Gym",
                    description = "Phòng gym đầy đủ trang thiết bị sẵn có 24/7.",
                    status = "unavailable"
                ),
                Service(
                    name = "Laundry",
                    description = "Các thiết bị giặt ủi hoạt động bằng tiền xu có sẵn trong tòa nhà.",
                    status = "available"
                ),
                Service(
                    name = "Parking",
                    description = "Chỗ đậu xe dành riêng cho mỗi căn hộ.",
                    status = "available"
                )
            )
        ),
        Apartment(
            id = "3",
            apartmentNumber = "C303",
            floor = 3,
            numBedrooms = 1,
            numBathrooms = 1,
            areaSqft = 800,
            rent = 1200,
            internetFee = 40,
            garbageFee = 15,
            status = "occupied",
            waterUsage = mapOf("current_usage" to 20, "max_usage" to 40),
            furniture = Furniture(
                beds = FurnitureItem(quantity = 1, damaged = 0),
                sofas = FurnitureItem(quantity = 1, damaged = 0),
                tables = FurnitureItem(quantity = 1, damaged = 0),
                chairs = FurnitureItem(quantity = 2, damaged = 0),
                appliances = Appliance(
                    quantity = 2,
                    damaged = 0,
                    items = listOf("refrigerator", "microwave")
                )
            ),
            tenant = Tenant(
                name = "Alice Smith",
                gender = "Female",
                contact = "alice.smith@example.com",
                phone = "555-123-4567",
                identityCardNumber = "GHI789012",
                moveInDate = "2023-07-01",
                leaseExpiryDate = "2024-06-30"
            ),
            services = listOf(
                Service(
                    name = "Swimming Pool",
                    description = "Hồ bơi ngoài trời mở cửa từ 8 giờ sáng đến 8 giờ tối.",
                    status = "available"
                ),
                Service(
                    name = "Gym",
                    description = "Phòng gym đầy đủ trang thiết bị sẵn có 24/7.",
                    status = "unavailable"
                )
            )
        )
    )
}
