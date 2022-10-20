package kr.hs.dgsw.hyeon.domain.usecase.center.local

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.hyeon.domain.model.Center
import kr.hs.dgsw.hyeon.domain.repository.center.LocalCenterRepository

class GetLocalCenterDataUseCase constructor(
    private val repository: LocalCenterRepository
) {
    operator fun invoke(): Flow<List<Center>> = repository.getCenterData()
}