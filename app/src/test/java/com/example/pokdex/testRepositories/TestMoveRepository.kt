package com.example.pokdex.testRepositories

import com.example.pokdex.data.repositories.MoveRepository
import com.example.pokdex.model.Move

class TestMoveRepository : MoveRepository {
    var moves = mutableListOf<Move>()
    override suspend fun insert(item: Move) {
        moves.add(item)
    }

    override suspend fun getMove(name: String): Move {
        return moves.first { move -> move.name == name }
    }

    override suspend fun retrieveMoves() {
        moves.addAll(listOf(
            Move("test1", "this is a testMove", "1 Action", listOf("testpower", "testpower2")),
            Move("test2", "this is a testMove", "1 Action", listOf("testpower", "testpower2")),
            Move("test3", "this is a testMove", "1 Action", listOf("testpower", "testpower2")),
            Move("test4", "this is a testMove", "1 Action", listOf("testpower", "testpower2"))
        ))
    }
}
