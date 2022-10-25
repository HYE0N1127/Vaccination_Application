package kr.hs.dgsw.hyeon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.hs.dgsw.hyeon.data.local.dao.CenterDao
import kr.hs.dgsw.hyeon.data.local.entity.CenterEntity

@Database(
    version = 1,
    entities = [CenterEntity::class],
    exportSchema = true
)
abstract class CenterDataBase : RoomDatabase() {
    abstract fun centerDao(): CenterDao
}