package programmers.q92344

/** https://school.programmers.co.kr/learn/courses/30/lessons/92344 */
/** 파괴되지 않은 건물 */
class Solution {

    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val m = board.size
        val n = board[0].size
        val temp = Array(m + 1) { Array(n + 1) { 0 } }

        for (s in skill) {
            val sign = if (s[0] == 1) -1 else 1
            temp[s[1]][s[2]] += sign * s[5]
            temp[s[1]][s[4] + 1] += -sign * s[5]
            temp[s[3] + 1][s[2]] += -sign * s[5]
            temp[s[3] + 1][s[4] + 1] += sign * s[5]
        }

        for (r in temp.indices) {
            for (c in 1 until n + 1) {
                temp[r][c] += temp[r][c - 1]
            }
        }

        for (c in 0 until n + 1) {
            for (r in 1 until m + 1) {
                temp[r][c] += temp[r - 1][c]
            }
        }

        var answer = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (board[i][j] + temp[i][j] > 0)
                    answer += 1
            }
        }

        return answer
    }
}
