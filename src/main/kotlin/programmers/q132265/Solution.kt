package programmers.q132265

/** https://school.programmers.co.kr/learn/courses/30/lessons/132265 */
/** 롤케이크 자르기 */
class Solution {

    fun solution(topping: IntArray): Int {
        val rightSide = mutableMapOf<Int, Int>().withDefault { 0 }
        topping.map { rightSide[it] = rightSide.getValue(it) + 1 }

        val leftSide = mutableMapOf<Int, Int>().withDefault { 0 }
        var answer = 0
        for (i: Int in topping) {
            leftSide[i] = leftSide.getValue(i) + 1
            rightSide[i] = rightSide.getValue(i) - 1

            if (rightSide.getValue(i) == 0)
                rightSide.remove(i)

            if (leftSide.size == rightSide.size)
                answer += 1
        }

        return answer
    }
}
