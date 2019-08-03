import com.sun.xml.internal.fastinfoset.util.StringArray

class OX () {
    var tableArr: Array<Array<String>>
    var currentTurn: String = "X"

    init {
        tableArr = Array(4) { i -> arrayOf("-", "-", "-", "-")}
        tableArr[0][0] = ""
        for (i in tableArr.indices) {
            for (j in tableArr[i].indices) {
                if (i == 0) {
                    tableArr[i][j] = j.toString()
                }
                if (j == 0) {
                    tableArr[i][j] = i.toString()
                }
            }
        }

        //game sequence
        showWelcome()
        showTable()
        showTurn()
        input()
    }

    fun detectError(inputList: List<String>) {
        if (inputList.size < 2) {
            showError()
            return
        } else if (inputList.size > 2) {
            showError()
            return
        }

        try {
            inputList[0].toInt()
            inputList[1].toInt()
        } catch (e: Throwable) {
            showError()
            return
        }

        val input1Number = inputList[0].toInt()
        val input2Number = inputList[1].toInt()

        if (input1Number > 3 || input2Number > 3) {
            showError()
            return
        } else if (input1Number < 1 || input2Number < 1)  {
            showError()
            return
        }

        if (tableArr[input1Number][input2Number] != "-") {
            showError()
            return
        }
        putMarker(input1Number, input2Number)
    }

    fun putMarker(x: Int, y: Int) {
        tableArr[x][y] = currentTurn
        if (detectWin()) {
            showResult()
        } else if (!detectDraw()) {
            showDrawResult()
        } else {
            switchTurn()
        }
    }

    fun detectWin(): Boolean {
        // horizontal
        for (i in 1..3) {
            if (tableArr[i][1] == currentTurn && tableArr[i][2] == currentTurn && tableArr[i][3] == currentTurn) {
                return true
            }
        }
        // vertical
        for (i in 1..3) {
            if (tableArr[1][i] == currentTurn && tableArr[2][i] == currentTurn && tableArr[3][i] == currentTurn) {
                return true
            }
        }
        // left diagonal
        if (tableArr[1][3] == currentTurn && tableArr[2][2] == currentTurn && tableArr[3][1] == currentTurn) {
            return true
        }
        // right diagonal
        if (tableArr[1][1] == currentTurn && tableArr[2][2] == currentTurn && tableArr[3][3] == currentTurn) {
            return true
        }
        return false
    }

    fun detectDraw(): Boolean {
        var emptyFlag: Boolean = false
        for (i in tableArr.indices) {
            for (j in tableArr[i].indices) {
                if (tableArr[i][j] == "-") {
                    emptyFlag = true
                }
            }
        }
        return emptyFlag
    }

    fun showTable() {
        for (i in tableArr.indices) {
            for (j in tableArr[i].indices) {
                print(tableArr[i][j] + " ")
            }
            println()
        }
    }

    fun input() {
        try {
            print("Please input Row Col: ")
            val stringInput = readLine()!!
            var inputArr: List<String> = stringInput.split(" ")
//            print(inputArr[0] + " " + inputArr[1])
            detectError(inputArr)
        } catch (e: Throwable) {
            showError()
        }
    }

    fun switchTurn() {
        if (currentTurn == "X") {
            currentTurn = "O"
        } else if (currentTurn == "O") {
            currentTurn = "X"
        }

        showTable()
        showTurn()
        input()
    }

    fun showTurn() {
        println(currentTurn + " Turn")
    }

    fun showWelcome() {
        println("Welcome to OX")
    }

    fun showError() {
        println("ERROR: You're enter the wrong number.")
        input()
    }

    fun showResult() {
        showTable()
        var loser: String = "?"
        if (currentTurn == "X") {
            loser = "O"
        } else if (currentTurn == "O") {
            loser = "X"
        }
        println(currentTurn + " Won " + loser + " Lose")
    }

    fun showDrawResult() {
        showTable()
        println("DRAW!")
    }
}


fun main() {
    var test = OX()
}