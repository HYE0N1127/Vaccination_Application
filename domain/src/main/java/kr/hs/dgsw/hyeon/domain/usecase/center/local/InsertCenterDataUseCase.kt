package kr.hs.dgsw.hyeon.domain.usecase.center.local

import kr.hs.dgsw.hyeon.domain.model.Center
import kr.hs.dgsw.hyeon.domain.repository.center.LocalCenterRepository

class InsertCenterDataUseCase constructor(
    private val repository: LocalCenterRepository
) {
    suspend operator fun invoke(center: Center) = repository.insertCenter(center)

}