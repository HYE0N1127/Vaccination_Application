package kr.hs.dgsw.hyeon.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.hyeon.data.local.entity.CenterEntity
import kr.hs.dgsw.hyeon.domain.model.Center

@Dao
interface CenterDao {

    @Query("SELECT * FROM center_table")
    suspend fun getCenterData(): List<CenterEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCenter(entity: CenterEntity)

    @Delete
    suspend fun deleteCenter(entity: CenterEntity)
}