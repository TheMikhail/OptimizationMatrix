package com.example.optimizationmatrix

import JonsonAlgorithm
import PetrovAlgorithm
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.optimizationmatrix.ui.theme.OptimizationMatrixTheme
import java.io.FileWriter
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OptimizationMatrixTheme {
                val initialState = MatrixFactory.create()
                val matrix = remember { mutableStateOf(initialState) }

                val finalMatrixByJonson = remember { mutableStateOf<Array<IntArray>?>(null) }
                val finalMatrixbyPetrov = remember { mutableStateOf<Array<IntArray>?>(null) }

                val indexPetrov = remember { mutableStateOf<List<Int>?>(null) }
                val indexJonson = remember { mutableStateOf<List<Int>?>(null) }

                val save = remember { mutableStateOf<Array<IntArray>?>(null) }

                Column(modifier = Modifier.padding(bottom = 5.dp), verticalArrangement = Arrangement.Bottom) {
                    outputMatrix(matrix.value)
                    Button(
                        onClick = { matrix.value = MatrixFactory.create()
                            finalMatrixByJonson.value = null
                            finalMatrixbyPetrov.value = null
                            indexJonson.value = null
                            indexPetrov.value = null
                        }
                    ) {
                        Text(text = "Создать матрицу")
                    }
                    Button(
                        onClick = {
                            finalMatrixByJonson.value = FinalMatrix(matrix.value, JonsonAlgorithm().sumPlaceInStep(
                                JonsonAlgorithm().firstStep(matrix.value),
                                JonsonAlgorithm().secondStep(matrix.value),
                                JonsonAlgorithm().fourStep(matrix.value)))
                            finalMatrixbyPetrov.value = null
                            indexPetrov.value = null

                            indexJonson.value = JonsonAlgorithm().sumPlaceInStep(
                                JonsonAlgorithm().firstStep(matrix.value),
                                JonsonAlgorithm().secondStep(matrix.value),
                                JonsonAlgorithm().fourStep(matrix.value))
                        }
                    ) {
                        Text(text = "Преобразовать методом Джонсона")
                    }
                    Button(
                        onClick = {
                            finalMatrixbyPetrov.value = FinalMatrix(matrix.value, PetrovAlgorithm().finalStep(matrix.value,
                                PetrovAlgorithm().FirstStep(matrix.value),
                                PetrovAlgorithm().SecondStep(matrix.value),
                                PetrovAlgorithm().thirdStep(matrix.value)))
                            finalMatrixByJonson.value = null
                            indexJonson.value = null

                            indexPetrov.value = PetrovAlgorithm().finalStep(matrix.value,
                                PetrovAlgorithm().FirstStep(matrix.value),
                                PetrovAlgorithm().SecondStep(matrix.value),
                                PetrovAlgorithm().thirdStep(matrix.value))

                        }
                    ) {
                        Text(text = "Преобразовать методом Петрова - Соколицына")
                    }
                    finalMatrixByJonson.value?.let { outputFinalMatrix(it) }
                    finalMatrixbyPetrov.value?.let { outputFinalMatrix(it) }
                    indexPetrov.value?.let { outputIndexList(it) }
                    indexJonson.value?.let { outputIndexList(it) }

                    Button(
                        onClick = {
                            if (finalMatrixbyPetrov != null){
                                matrixInFile(finalMatrixbyPetrov.value)
                            }
                            else if (finalMatrixByJonson != null )
                                matrixInFile(finalMatrixByJonson.value)

                        }
                    ) {
                        Text(text = "Сохранить матрицу")
                    }
                }

            }
    }
}
fun matrixInFile(matrix: Array<IntArray>?){
    if (matrix == null) {
        println("Matrix is null")
        return
    }
    else{
        try {
            FileWriter("matrix.txt", false).use { writer ->
                for (i in 0 until matrix.size) {
                    for (j in 0 until matrix[0].size) {
                        writer.write("${matrix[i][j]} ")
                    }
                    writer.append('\n')
                }
                writer.close()
            }

        } catch (ex: IOException) {
            println(ex.message)
        }
    }
}
@Composable
fun outputMatrix(matrix: Array<IntArray>) {
    val countMachine = matrix.size
    val countDetails = matrix[0].size
    Column {
        for (i in 0 until countMachine) {
            Row(
                modifier = Modifier.padding(start = 5.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                for (j in 0 until countDetails) {
                    Text(text = "${matrix[i][j]}|")
                }
            }
        }
    }

}
    @Composable
    fun outputIndexList(indexList: List<Int>){
        Text("$indexList")
    }
fun FinalMatrix(matrix: Array<IntArray>, r: List<Int>): Array<IntArray> {
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
@Composable
fun outputFinalMatrix(matrix: Array<IntArray>) {
    val countMachine = matrix.size
    val countDetails = matrix[0].size
    Column {
        for (i in 0 until countMachine) {
            Row(
                modifier = Modifier.padding(start = 5.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                for (j in 0 until countDetails) {
                    Text(text = "${matrix[i][j]}|")
                }
            }
        }
    }
    }
}