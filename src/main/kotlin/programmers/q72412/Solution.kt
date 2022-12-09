package programmers.q72412

/** https://school.programmers.co.kr/learn/courses/30/lessons/72412 */
/** 순위 검색 */
class Solution {

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val answer = mutableListOf<Int>()
        val hashTable = HashMap<String, MutableList<Int>>().withDefault { mutableListOf() }

        for (elem in info) {
            val infoSplit = elem.split(" ")

            for (i in 0 until (1 shl 4)) {
                var key = ""
                for (j in 0 until 4) {
                    key += if (i.and(1 shl j) > 0) infoSplit[j] else "-"
                }
                val scoreList = hashTable.getValue(key)
                scoreList.add(infoSplit[4].toInt())
                hashTable[key] = scoreList
            }
        }

        hashTable.map { it.value.sort() }

        for (q in query) {
            val qSplit = q.split(" ")
            val scoreList = hashTable.getValue(qSplit[0] + qSplit[2] + qSplit[4] + qSplit[6])
            val idx = lowerBound(scoreList, qSplit[7].toInt())

            answer.add(scoreList.size - idx)
        }

        return answer.toIntArray()
    }

    private fun lowerBound(list: List<Int>, target: Int): Int {
        var low = 0
        var high = list.size

        while (low < high) {
            val mid = (low + high) / 2

            if (list[mid] >= target)
                high = mid
            else
                low = mid + 1
        }

        return low
    }
}
