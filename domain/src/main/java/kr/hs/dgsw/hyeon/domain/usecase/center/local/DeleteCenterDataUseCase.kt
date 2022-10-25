package kr.hs.dgsw.hyeon.domain.usecase.center.local

import kr.hs.dgsw.hyeon.domain.model.Center
import kr.hs.dgsw.hyeon.domain.repository.center.LocalCenterRepository

class DeleteCenterDataUseCase constructor(
    private val repository: LocalCenterRepository
) {
    suspend operator fun invoke(center: Center) = repository.deleteCenter(center)
}