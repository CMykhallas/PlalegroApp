@Entity(tableName = "content")
data class ContentEntity(
    @PrimaryKey val id: String,
    val title: String,
    val packVersion: Int,
    val state: String,
    val metadataJson: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val age: Int,
    val locale: String,
    val registeredAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey val key: String,
    val value: String
)
