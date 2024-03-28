import androidx.compose.runtime.Composable
class PetrovAlgorithm {

    fun SumMatrixFirstStep(matrix: Array<IntArray>): Array<IntArray> {

        val sumList = mutableListOf<Int>()
        val matrixCopy = matrix.map { innerArray -> innerArray.copyOf() }.toTypedArray()
        val copMatrix = matrix.map { innerArray -> innerArray.copyOf() }.toTypedArray()


        for (indexRow in 0 until matrix[0].size) {
            var summa = 0
            for (indexColumn in 0 until matrix.size - 1) {
                summa += matrix[indexColumn][indexRow]
            }
            sumList.add(summa)
        }

        for (n in 0 until FirstStep(matrix).size) {
            for (i in 0 until matrixCopy.size) {
                for (j in 0 until matrixCopy[0].size) {
                    if (FirstStep(matrix)[n] == j)
                        matrixCopy[i][n] = copMatrix[i][j]
                }
            }
        }
        for (i in 0 until 1)
            for (j in 1 until matrixCopy[i].size)
                matrixCopy[i][j] = matrixCopy[i][j] + matrixCopy[i][j - 1]

        for (i in 0 until 1)
            for (j in 1 until matrixCopy.size)
                matrixCopy[j][i] = matrixCopy[j][i] + matrixCopy[j - 1][i]

        for (i in 1 until matrixCopy.size)
            for (j in 1 until matrixCopy[i].size){
                matrixCopy[i][j] = matrixCopy[i][j] + maxOf(matrixCopy[i - 1][j], matrixCopy[i][j - 1])
            }
        return matrixCopy
    }
    fun SumMatrixSecondStep(matrix: Array<IntArray>): Array<IntArray> {

        val sumList = mutableListOf<Int>()
        val matrixCopy = matrix.map { innerArray -> innerArray.copyOf() }.toTypedArray()
        val copMatrix = matrix.map { innerArray -> innerArray.copyOf() }.toTypedArray()

        for (indexRow in 0 until matrix[0].size) {
            var summa = 0
            for (indexColumn in 0 until matrix.size - 1) {
                summa += matrix[indexColumn][indexRow]
            }
            sumList.add(summa)
        }
        for (n in 0 until SecondStep(matrix).size) {
            for (i in 0 until matrixCopy.size) {
                for (j in 0 until matrixCopy[0].size) {
                    if (SecondStep(matrix)[n] == j)
                        matrixCopy[i][n] = copMatrix[i][j]
                }
            }
        }
        for (i in 0 until 1)
            for (j in 1 until matrixCopy[i].size)
                matrixCopy[i][j] = matrixCopy[i][j] + matrixCopy[i][j - 1]

        for (i in 0 until 1)
            for (j in 1 until matrixCopy.size)
                matrixCopy[j][i] = matrixCopy[j][i] + matrixCopy[j - 1][i]

        for (i in 1 until matrixCopy.size)
            for (j in 1 until matrixCopy[i].size){
                matrixCopy[i][j] = matrixCopy[i][j] + maxOf(matrixCopy[i - 1][j], matrixCopy[i][j - 1])
            }

        return matrixCopy
    }

    fun SumMatrixThirdStep(matrix: Array<IntArray>): Array<IntArray> {
        val sumList = mutableListOf<Int>()
        val matrixCopy = matrix.map { innerArray -> innerArray.copyOf() }.toTypedArray()
        val copMatrix = matrix.map { innerArray -> innerArray.copyOf() }.toTypedArray()

        for (indexRow in 0 until matrix[0].size) {
            var summa = 0
            for (indexColumn in 0 until matrix.size - 1) {
                summa += matrix[indexColumn][indexRow]
            }
            sumList.add(summa)
        }

        for (n in 0 until thirdStep(matrix).size) {
            for (i in 0 until matrixCopy.size) {
                for (j in 0 until matrixCopy[0].size) {
                    if (thirdStep(matrix)[n] == j)
                        matrixCopy[i][n] = copMatrix[i][j]
                }
            }
        }
        for (i in 0 until 1)
            for (j in 1 until matrixCopy[i].size)
                matrixCopy[i][j] = matrixCopy[i][j] + matrixCopy[i][j - 1]

        for (i in 0 until 1)
            for (j in 1 until matrixCopy.size)
                matrixCopy[j][i] = matrixCopy[j][i] + matrixCopy[j - 1][i]

        for (i in 1 until matrixCopy.size)
            for (j in 1 until matrixCopy[i].size){
                matrixCopy[i][j] = matrixCopy[i][j] + maxOf(matrixCopy[i - 1][j], matrixCopy[i][j - 1])
            }

        return matrixCopy
    }

    fun FirstStep(matrix: Array<IntArray>): List<Int> {
        val outputIndex = mutableListOf<Int>()
        val sumList = mutableListOf<Int>()

        for (j in 0 until matrix[0].size) {
            var summa = 0
            for (k in 0 until matrix.size - 1) {
                summa += matrix[k][j]
            }
            sumList.add(summa)
        }
        val sortedSumList = sumList.sorted().toList()
        for (i in sortedSumList)
            for (j in 0 until sumList.size) {
                if (sumList[j] == i)
                    outputIndex.add(j + 1)
            }
        val index = outputIndex.distinct().toMutableList()
        return index
    }


    fun SecondStep(matrix: Array<IntArray>): List<Int> {
        val outputIndex = mutableListOf<Int>()
        val sum = mutableListOf<Int>()

        for (j in 0 until matrix[0].size) {
            var summa = 0
            for (k in 0 until matrix.size) {
                if (k != 0)
                    summa += matrix[k][j]
            }
            sum.add(summa)
        }
        val s = sum.sortedDescending().toList()
        for (i in s)
            for (j in 0 until sum.size) {
                if (sum[j] == i)
                    outputIndex.add(j + 1)
            }

        val index = outputIndex.distinct().toMutableList()
        return index
    }

    fun thirdStep(matrix: Array<IntArray>): List<Int>{
        val sortedIndex = mutableListOf<Int>()

        val dif = mutableListOf<Int>()
        for (i in 0 until 1)
            for (j in 0 until matrix[0].size){
                dif.add(matrix[matrix.size-1][j] - matrix[0][j])
            }

        val matrixCopy = dif.sortedDescending()
        for (k in matrixCopy)
            for (i in 0 until 1)
                for (j in 0 until matrix[0].size){
                    if(k == matrix[matrix.size-1][j] - matrix[0][j])
                        sortedIndex.add(j+1)
                }
        val index = sortedIndex.distinct().toMutableList()
        return index
    }

    fun finalStep(matrix: Array<IntArray>, sortedInd1: List<Int>,sortedInd2: List<Int>, sortedInd3: List<Int>): List<Int>{
        val lastIndex1matrix = SumMatrixFirstStep(matrix)[matrix.size-1][matrix[0].size-1]
        val lastIndex2Step = SumMatrixSecondStep(matrix)[matrix.size-1][matrix[0].size-1]
        val lastIndex3Step = SumMatrixThirdStep(matrix)[matrix.size-1][matrix[0].size-1]

        if (lastIndex1matrix < lastIndex2Step  && lastIndex1matrix < lastIndex3Step)
            return sortedInd1
        else if(lastIndex2Step < lastIndex1matrix &&  lastIndex2Step < lastIndex3Step)
            return sortedInd2
        else
            return sortedInd3
    }
}