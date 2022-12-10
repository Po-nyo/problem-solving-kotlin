package programmers.q72413


import java.util.*
import kotlin.math.min

/** https://school.programmers.co.kr/learn/courses/30/lessons/72413 */
/** 합승 택시 요금 */
class Solution {

    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val edges = Array<MutableList<List<Int>>>(n + 1) { mutableListOf() }

        for ((c, d, f) in fares) {
            edges[c].add(listOf(d, f))
            edges[d].add(listOf(c, f))
        }

        val costList = mutableListOf<IntArray>()
        for (start in arrayOf(s, a, b)) {
            costList.add(dijkstra(n, edges, start))
        }

        var answer = Int.MAX_VALUE
        for (i in 1..n) {
            answer = min(answer, costList[0][i] + costList[1][i] + costList[2][i])
        }

        return answer
    }

    private fun dijkstra(n: Int, edges: Array<MutableList<List<Int>>>, start: Int): IntArray {
        val costs = IntArray(n + 1) { Int.MAX_VALUE }
        val pq = PriorityQueue<List<Int>> { a, b -> a.component1() - b.component1() }
        costs[start] = 0
        pq.add(listOf(0, start))

        while (pq.isNotEmpty()) {
            val (cost, now) = pq.poll()

            if (cost > costs[now])
                continue

            for ((nxt, w) in edges[now]) {
                val newCost = cost + w

                if (newCost < costs[nxt]) {
                    costs[nxt] = newCost
                    pq.add(listOf(newCost, nxt))
                }
            }
        }

        return costs
    }
}
