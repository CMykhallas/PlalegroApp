package com.pLg.shared.data

import com.pLg.shared.domain.*

public interface UserRepository {
    public suspend fun save(user: UserDto): Result<Unit, Failure>
    public suspend fun findById(id: String): Result<UserDto, Failure>
    public suspend fun all(): Result<List<UserDto>, Failure>
}

public interface ChildRepository {
    public suspend fun save(child: ChildProfileDto): Result<Unit, Failure>
    public suspend fun findById(id: String): Result<ChildProfileDto, Failure>
    public suspend fun all(): Result<List<ChildProfileDto>, Failure>
}

public interface ContentPackRepository {
    public suspend fun save(pack: ContentPackDto): Result<Unit, Failure>
    public suspend fun findById(id: String): Result<ContentPackDto, Failure>
    public suspend fun all(): Result<List<ContentPackDto>, Failure>
}

// Implementação em memória (útil para testes e protótipos)
public class InMemoryUserRepository : UserRepository {
    private val store = linkedMapOf<String, UserDto>()

    override suspend fun save(user: UserDto): Result<Unit, Failure> {
        store[user.id] = user
        return Result.ok(Unit)
    }

    override suspend fun findById(id: String): Result<UserDto, Failure> {
        val value = store[id] ?: return Result.err(ValidationFailure.Custom("User not found: $id"))
        return Result.ok(value)
    }

    override suspend fun all(): Result<List<UserDto>, Failure> = Result.ok(store.values.toList())
}

public class InMemoryChildRepository : ChildRepository {
    private val store = linkedMapOf<String, ChildProfileDto>()

    override suspend fun save(child: ChildProfileDto): Result<Unit, Failure> {
        store[child.id] = child
        return Result.ok(Unit)
    }

    override suspend fun findById(id: String): Result<ChildProfileDto, Failure> {
        val value = store[id] ?: return Result.err(ValidationFailure.Custom("Child not found: $id"))
        return Result.ok(value)
    }

    override suspend fun all(): Result<List<ChildProfileDto>, Failure> = Result.ok(store.values.toList())
}

public class InMemoryContentPackRepository : ContentPackRepository {
    private val store = linkedMapOf<String, ContentPackDto>()

    override suspend fun save(pack: ContentPackDto): Result<Unit, Failure> {
        store[pack.id] = pack
        return Result.ok(Unit)
    }

    override suspend fun findById(id: String): Result<ContentPackDto, Failure> {
        val value = store[id] ?: return Result.err(ValidationFailure.Custom("Content pack not found: $id"))
        return Result.ok(value)
    }

    override suspend fun all(): Result<List<ContentPackDto>, Failure> = Result.ok(store.values.toList())
}
