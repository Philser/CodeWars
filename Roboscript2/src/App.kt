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
        var xDimension = 1
        var yDimension = 1
        var directionX = 1 // Facing right
        var directionY = 0 // Not facing up/down

        for (token in code) {
            if (token.equals('F')) {
                // Move
                currX += directionX
                currY += directionY
                if (currX < 0 && directionX < 0) startX += 1
                if (currY < 0 && directionY < 0) startY += 1
                if (abs(currX) > xDimension) xDimension = abs(currX)
                if (abs(currY) > yDimension) yDimension = abs(currY)
            } else if (token.equals('L')) {
                // Turn left
                if(directionY == 0) { // Turn facing up- or downwards
                    directionY = directionX * -1 // Will be the inverse of the curr X
                    directionX = 0
                } else { // Turn facing left or right
                    directionX = directionY
                    directionY = 0
                }
            } else if (token.equals('R')) {
                // Turn right
                if (directionY == 0) { // Turn facing up- or downwards
                    directionX = directionY
                    directionY = 0
                } else { // Turn facing left or right
                    directionX = directionY * -1 // Will be the inverse of the curr X
                    directionY = 0
                }
            }
        }

        return arrayOf(xDimension + 1, yDimension + 1, startX, startY)
    }

    private fun moveRobotOnGrid(code:String, grid: Array<Array<String>>, startX: Int, startY: Int) {
        var currX = startX
        var currY = startY
        var directionX = 1 // Facing right
        var directionY = 0 // Not facing up/down
        for (token in code) {
            if (token.equals('F')) {
                // Move
                currX += directionX
                currY += directionY
                grid[currY][currX] = "*"
            } else if (token.equals('L')) {
                // Turn left
                if(directionY == 0) { // Turn facing up- or downwards
                    directionY = directionX * -1 // Will be the inverse of the curr X
                    directionX = 0
                } else { // Turn facing left or right
                    directionX = directionY
                    directionY = 0
                }
            } else if (token.equals('R')) {
                // Turn right
                if (directionY == 0) { // Turn facing up- or downwards
                    directionX = directionY
                    directionY = 0
                } else { // Turn facing left or right
                    directionX = directionY * -1 // Will be the inverse of the curr X
                    directionY = 0
                }
            }
        }
    }

    fun main() {
       System.out.print(execute("FFFFFLFFFFFLFFFFFLFFFFFL"))
    }
}