package programmers.q72414

/** https://school.programmers.co.kr/learn/courses/30/lessons/72414 */
/** 광고 삽입 */
class Solution {

    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val playSec = toSeconds(play_time)
        val advSec = toSeconds(adv_time)
        val prefixSum = LongArray((playSec + 1).toInt())

        for (log in logs) {
            val times = log.split("-")
            val startTime = toSeconds(times[0])
            val endTime = toSeconds(times[1])

            prefixSum[startTime.toInt()] += 1L
            prefixSum[endTime.toInt()] -= 1L
        }

        for (i in 1..2) {
            for (j in 1 until prefixSum.size) {
                prefixSum[j] += prefixSum[j - 1]
            }
        }

        var answer = 0L
        var max = 0L
        for (sec in 0..(playSec - advSec)) {
            val now = prefixSum[(sec + advSec - 1).toInt()] - if (sec > 0) prefixSum[(sec - 1).toInt()] else 0
            if (now > max) {
                answer = sec
                max = now
            }
        }

        return toTime(answer)
    }

    private fun toSeconds(time: String): Long {
        val timeSplit = time.split(":").map { it.toLong() }
        return timeSplit[0] * 60 * 60 + timeSplit[1] * 60 + timeSplit[2]
    }

    private fun toTime(seconds: Long): String {
        return arrayOf(seconds / (60 * 60), (seconds % (60 * 60)) / 60, seconds % 60)
            .joinToString(":") { it.toString().padStart(2, '0') }
    }

}