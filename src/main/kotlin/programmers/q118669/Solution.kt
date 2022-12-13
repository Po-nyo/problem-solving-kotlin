package programmers.q118669

import java.util.PriorityQueue
import kotlin.math.max

/** https://school.programmers.co.kr/learn/courses/30/lessons/118669 */
/** 등산코드 정하기 */
class Solution {

    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val edges = Array<MutableList<Pair<Int, Int>>>(n + 1) { mutableListOf() }

        for ((i, j, w) in paths) {
            edges[i].add(Pair(w, j))
            edges[j].add(Pair(w, i))
        }

        val intensity = IntArray(n + 1) { Int.MAX_VALUE }
        val pq = PriorityQueue<Pair<Int, Int>> { a, b -> a.first - b.first }

        for (i in gates) {
            intensity[i] = 0
            pq.add(Pair(0, i))
        }

        while (pq.isNotEmpty()) {
            val (intensityNow, now) = pq.poll()

            if (intensityNow > intensity[now] || now in summits)
                continue

            for ((w, to) in edges[now]) {
                val newIntensity = max(intensityNow, w)

                if (newIntensity < intensity[to]) {
                    intensity[to] = newIntensity
                    pq.add(Pair(newIntensity, to))
                }
            }
        }

        val answer = intArrayOf(summits[0], Int.MAX_VALUE)
        for (i in summits.sorted()) {
            if (intensity[i] < answer[1]) {
                answer[0] = i
                answer[1] = intensity[i]
            }
        }

        return answer
    }
}
