import kotlin.math.abs

class App {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            (App()).main()
        }
    }

    fun execute(code: String): String {
        // Parse char
        var dimensions: Array<Int> = getDimensions(code)
        var grid: Array<Array<String>> = Array(dimensions[1], {(Array(dimensions[0], {" "}))})

        // Execute action
        moveRobotOnGrid(code, grid, dimensions[2], dimensions[3])

        // Store state in data structure
        return printGrid(grid) // Implement your RS1 interpreter here
    }

    private fun printGrid(grid: Array<Array<String>>): String {
        var sb: StringBuilder = StringBuilder("")
        for (line in grid) {
            for (cell in line) {
                sb.append(cell)
            }
            sb.append("\r\n")
        }
        val final = sb.toString().removeSuffix("\r\n")
        return final
    }

    // Returns the x and y dimensions of the grid, and the x and y coordinates of the 0,0 point
    private fun getDimensions(code: String): Array<Int> {
        var currX = 0
        var currY = 0
        var startX = currX // The 0 x point in the final grid
        var startY = currY // The 0 y point in the final grid
        var xDimensionPos = 1
        var yDimensionPos = 1
        var xDimensionNeg = 0
        var yDimensionNeg = 0
        var directionX = 1 // Facing right
        var directionY = 0 // Not facing up/down

        val regex = Regex("([LFR])([0-9]*)")
        val matches = regex.findAll(code)

        var token = ""
        var repeat = 0
        for (match in matches) {
            token = match.groupValues[1] // Token
            repeat = if(match.groupValues[2] != "") match.groupValues[2].toInt() else 1
            if (token == "F") {
                // Move
                for (i in 0 until repeat) {
                    currX += directionX
                    currY += directionY
                    if (currX < 0 && directionX < 0) startX += 1
                    if (currY < 0 && directionY < 0) startY += 1
                    if (currX > xDimensionPos) xDimensionPos = currX // Track the furthest we have moved in positive X direction
                    if (currX < xDimensionNeg) xDimensionNeg = currX // Track the furthest we have moved in negative X direction
                    if (currY > yDimensionPos) yDimensionPos = currY
                    if (currY < yDimensionNeg) yDimensionNeg = currY
                }
            } else if (token == "L" || token == "R") {
                for (i in 0 until repeat) {
                    val dirPair = getNewDirection(directionX, directionY, token)
                    directionX = dirPair.first
                    directionY = dirPair.second
                }
            }
        }

        return arrayOf(abs(xDimensionNeg) + xDimensionPos + 1, abs(yDimensionNeg) + yDimensionPos + 1, startX, startY)
    }

    private fun moveRobotOnGrid(code:String, grid: Array<Array<String>>, startX: Int, startY: Int) {
        var currX = startX
        var currY = startY
        var directionX = 1 // Facing right
        var directionY = 0 // Not facing up/down

        val regex = Regex("([LFR])([0-9]*)")
        val matches = regex.findAll(code)

        var token = ""
        var repeat = 0
        for (match in matches) {
            token = match.groupValues[1] // Token
            repeat = if(match.groupValues[2] != "") match.groupValues[2].toInt() else 1
            if (token == "F") {
                // Move
                for (i in 0 until repeat) {
                    // Move
                    currX += directionX
                    currY += directionY
                    grid[currY][currX] = "*"
                }
            } else if (token == "L" || token == "R") {
                for (i in 0 until repeat) {
                    grid[currY][currX] = "*"
                    val dirPair = getNewDirection(directionX, directionY, token)
                    directionX = dirPair.first
                    directionY = dirPair.second
                }
            }
        }
    }

//    private fun navigateOnGrid(code: String, grid: Array<Array<String>>?): Array<Int> {
//
//    }

    private fun getNewDirection(currXOrientation: Int, currYOrientation: Int, turn: String): Pair<Int, Int> {
        var directionX = currXOrientation
        var directionY = currYOrientation
        if (turn.equals("L")) {
            // Turn left
            if(directionY == 0) { // Turn facing up- or downwards
                directionY = directionX * -1 // Will be the inverse of the curr X
                directionX = 0
            } else { // Turn facing left or right
                directionX = directionY
                directionY = 0
            }
        } else if (turn.equals("R")) {
            // Turn right
            if (directionY == 0) { // Turn facing up- or downwards
                directionY = directionX
                directionX = 0
            } else { // Turn facing left or right
                directionX = directionY * -1 // Will be the inverse of the curr X
                directionY = 0
            }
        }

        return Pair(directionX, directionY)
    }

    fun main() {
       System.out.print(execute("LF5R1F3R1F3R1F7"))
    }
}