object MatrixFactory {
    private fun CreateMatrix(machineSize: Int, detailsSize: Int): Array<IntArray> {
        val matrix: Array<IntArray> = Array(machineSize) { IntArray(detailsSize) { 0 } }
        val countMachine = matrix.size
        val countDetails = matrix[0].size
        for (i in 0 until countMachine) {
            for (j in 0 until countDetails) {
                matrix[i][j] = (Math.random() * 10).toInt()
            }
        }
        return matrix
    }

    fun create(): Array<IntArray> {
        return CreateMatrix(getMachineSize(), getDetailsSize())
    }

    fun getMachineSize(): Int {
        val size = (Math.random() * 10).toInt() + 1
        return size
    }

    fun getDetailsSize(): Int {
        val size = (Math.random() * 10).toInt() + 1
        return size
    }

    private fun createFinalMatrix(matrix: Array<IntArray>, r: List<Int>): Array<IntArray> {
        val matrixCopy = matrix.map { innerArray -> innerArray.copyOf() }.toTypedArray()
        val copMatrix = matrix.map { innerArray -> innerArray.copyOf() }.toTypedArray()
        for (n in 0 until r.size) {
            for (i in 0 until matrixCopy.size) {
                for (j in 0 until matrixCopy[0].size) {
                    if (r[n] - 1 == j) {
                        matrixCopy[i][n] = copMatrix[i][j]
                    }
                }
            }
        }
        return matrixCopy
    }
}