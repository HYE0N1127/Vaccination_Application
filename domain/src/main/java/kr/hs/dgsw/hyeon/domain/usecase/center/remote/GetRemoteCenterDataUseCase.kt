package kr.hs.dgsw.hyeon.domain.usecase.center.remote

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.hyeon.domain.model.Center
import kr.hs.dgsw.hyeon.domain.repository.center.RemoteCenterRepository

class GetRemoteCenterDataUseCase constructor(
    private val repository: RemoteCenterRepository
) {
    operator fun invoke(page: Int): Flow<List<Center>> = repository.getRemoteCenterData(page)
}