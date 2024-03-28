import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min

class JonsonAlgorithm {
    fun copyArray(source: IntArray): IntArray {
        return source.copyOfRange(0, source.size)
    }


    fun firstStep(matrix: Array<IntArray>): List<Int> {
        var fstInd = mutableListOf<Int>()

        var matrixCopy: IntArray
        matrixCopy = copyArray(matrix[0])
        matrixCopy.sort()

        for (i in matrixCopy) {
            for (j in 0 until 1) {
                for (k in 0 until matrix[j].size) {
                    if (matrix[j][k] == i)
                        fstInd.add(k + 1)
                }
            }
        }

        return fstInd.distinct()
    }


    fun secondStep(matrix: Array<IntArray>): List<Int> {
        val fstInd = mutableListOf<Int>()
        val matrixCopy: IntArray
        matrixCopy = copyArray(matrix[matrix.size - 1])
        matrixCopy.sortDescending()
        for (i in matrixCopy) {
            for (j in matrix.size - 1 until matrix.size) {
                for (k in 0 until matrix[j].size) {
                    if (matrix[j][k] == i)
                        fstInd.add(k + 1)
                }
            }
        }
        return fstInd.distinct()
    }


    fun fourStep(matrix: Array<IntArray>): List<Int> {
        val fstInd = mutableListOf<Int>()
        val sum = mutableListOf<Int>()
        for (j in 0 until matrix[0].size) {
            var summa = 0
            for (k in 0 until matrix.size) {
                summa += matrix[k][j]
            }
            sum.add(summa)
        }
        val matrixCopy: IntArray
        matrixCopy = copyArray(sum.toIntArray())
        matrixCopy.sortDescending()
        for (i in matrixCopy) {
            for (j in 0 until sum.size) {
                if (sum[j] == i)
                    fstInd.add(j + 1)
            }
        }
        return fstInd.distinct()
    }

    fun sumPlaceInStep(indFirstStep: List<Int>, indSecondStep: List<Int>, indFourStep: List<Int>): List<Int> {
        val r = mutableListOf<Int>()
        val sum = mutableListOf<Int>()
        for (i in 1..indFirstStep.size){
            var sumTemp = 0
            for (a in 0 until indFirstStep.size)
                if (i == indFirstStep[a])
                    sum.add(a+1)
            for (b in 0 until indSecondStep.size)
                if (i == indSecondStep[b])
                    sum[i-1]+=(b+1)
            for (c in 0 until indFourStep.size)
                if (i == indFourStep[c])
                    sum[i-1]+=(c+1)
        }
        val sumCopy = sum.sorted()

        for (i in sumCopy) {
            for (j in 0 until sum.size)
                if (i == sum[j])
                    r.add(j + 1)
        }
        val indFinal = r.distinct()
        return indFinal
    }

}

