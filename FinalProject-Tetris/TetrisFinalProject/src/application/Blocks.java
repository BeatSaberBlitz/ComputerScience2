/**
 * 
 */
package application;

import java.util.Random;

/**
 * Creates and runs a back end base for the game to run on
 * 
 * @author Tyler Marefke
 * @version 1.0.0
 * @date 4/28/2020
 */
public class Blocks {
	private Random randomGen;
	private int blockKeyCode;
	private int nextBlockKeyCode;
	private int rotateDirectionCode;
	private int numRowsDeleted;
	private int[][] tetrisGridBackground;
	private int[][] nextBlockGridBackground;
	private int[][] blockPosition;

	/**
	 * Sets up the block class arrays and creates the first block of the game
	 */
	public Blocks() {
		numRowsDeleted = 0;
		rotateDirectionCode = 1;
		tetrisGridBackground = new int[20][10];
		nextBlockGridBackground = new int[2][4];
		blockPosition = new int[4][2];
		for (int row = 0; row < tetrisGridBackground.length; row++) {
			for (int col = 0; col < tetrisGridBackground[row].length; col++) {
				tetrisGridBackground[row][col] = 0;
			}
		}
		for (int row = 0; row < nextBlockGridBackground.length; row++) {
			for (int col = 0; col < nextBlockGridBackground[0].length; col++) {
				nextBlockGridBackground[row][col] = 0;
			}
		}
		GenerateNextBlock();
		CreateBlock();
	}

	/**
	 * Returns the text based array of the tetris game
	 * 
	 * @return the tetrisGridBackground
	 */
	public int[][] getTetrisGridBackground() {

		return tetrisGridBackground;
	}

	/**
	 * Returns the text based array of the nextBlock array
	 * 
	 * @return
	 */
	public int[][] getNextBlockGridBackground() {
		return nextBlockGridBackground;
	}

	/**
	 * Returns the number of rows deleted in the board
	 * 
	 * @return numRowsDeleted
	 */
	public int getNumRowsDeleted() {
		return numRowsDeleted;
	}

	/**
	 * Deletes the rows that have been completely filled in the board
	 */
	public void DeleteRow() {
		int filledRowCounter = 0;
		int rowPos = 0;

		for (int row = tetrisGridBackground.length - 1; row >= 0; row--) {
			filledRowCounter = 0;
			for (int col = tetrisGridBackground[row].length - 1; col >= 0; col--) {
				if (tetrisGridBackground[row][col] != 0) {
					filledRowCounter++;
				}
			}

			// Checks if filledRowCounter is equal to ten which indicates a completed row
			if (filledRowCounter == 10) {
				rowPos = row;
				numRowsDeleted++;
				for (int col = 0; col < tetrisGridBackground[0].length; col++) {
					tetrisGridBackground[row][col] = 0;
				}
				break;
			}
		}

		if (filledRowCounter == 10) {
			// Runs the ShiftBoardDown method to shift the blocks above the deleted rows
			// down to the respective positions
			ShiftBoardDown(rowPos);
		}
	}

	/**
	 * Shifts the board down once a row has been cleared
	 * 
	 * @param myRowPos    the position of the row that was cleared
	 * @param rowsDeleted the number of rows deleted and to be moved down
	 */
	public void ShiftBoardDown(int myRowPos) {

		// The position of the row that was deleted
		int rowPos = myRowPos;

		for (int row = rowPos; row >= 0; row--) {
			for (int col = tetrisGridBackground[row].length - 1; col >= 0; col--) {

				// Shifts blocks down based on the number of rows deleted
				// if statement is to make sure it doesn't break upon reaching the top row
				if (row != 0) {
					tetrisGridBackground[row][col] = tetrisGridBackground[row - 1][col];
					tetrisGridBackground[row - 1][col] = 0;
				} else {
					tetrisGridBackground[row][col] = 0;
				}
			}
		}
		DeleteRow();
	}

	/**
	 * Shifts the movable block to the right one column
	 */
	public void BlockMoveRight() {
		int counter = 0;

		// outer is there to break from the loop once the block has been moved
		outer: for (int col = tetrisGridBackground[0].length - 1; col >= 0; col--) {
			for (int row = tetrisGridBackground.length - 1; row >= 0; row--) {

				// Tests to make sure that the number is greater than zero
				if (tetrisGridBackground[row][col] > 0) {
					if (col != 9 && tetrisGridBackground[row][col + 1] == 0) {
						tetrisGridBackground[row][col + 1] = tetrisGridBackground[row][col];
						tetrisGridBackground[row][col] = 0;

						// Tracks the last know position of the entire block
						blockPosition[counter][0] = row;
						blockPosition[counter][1] = col;

						counter++;
					} else {

						// If counter is not equal to 4 than the blocks the were shifted are shifted
						// back to their last know position
						if (counter != 4) {
							counter--;

							for (; counter >= 0; counter--) {
								tetrisGridBackground[blockPosition[counter][0]][blockPosition[counter][1]] = tetrisGridBackground[blockPosition[counter][0]][blockPosition[counter][1]
										+ 1];
								tetrisGridBackground[blockPosition[counter][0]][blockPosition[counter][1] + 1] = 0;
							}
						}

						break outer;
					}
				}
			}
		}
	}

	/**
	 * Shifts the movable block to the left one column
	 */
	public void BlockMoveLeft() {
		int counter = 0;

		// outer is there to break from the loop once the block has been moved
		outer: for (int col = 0; col <= tetrisGridBackground[0].length - 1; col++) {
			for (int row = tetrisGridBackground.length - 1; row >= 0; row--) {

				// Tests to make sure that the number is greater than zero
				if (tetrisGridBackground[row][col] > 0) {
					if (col != 0 && tetrisGridBackground[row][col - 1] == 0) {
						tetrisGridBackground[row][col - 1] = tetrisGridBackground[row][col];
						tetrisGridBackground[row][col] = 0;

						// Tracks the last know position of the entire block
						blockPosition[counter][0] = row;
						blockPosition[counter][1] = col;

						counter++;
					} else {

						// If counter is not equal to 4 than the blocks the were shifted are shifted
						// back to their last know position
						if (counter != 4) {
							counter--;

							for (; counter >= 0; counter--) {
								tetrisGridBackground[blockPosition[counter][0]][blockPosition[counter][1]] = tetrisGridBackground[blockPosition[counter][0]][blockPosition[counter][1]
										- 1];
								tetrisGridBackground[blockPosition[counter][0]][blockPosition[counter][1] - 1] = 0;
							}
						}

						break outer;
					}
				}
			}
		}
	}

	/**
	 * Shifts the movable block down one row
	 */
	public int BlockMoveDown() {
		int counter = 0;

		// outer is there to break from the loop once the block has been moved
		for (int row = tetrisGridBackground.length - 1; row >= 0; row--) {
			for (int col = tetrisGridBackground[row].length - 1; col >= 0; col--) {

				// Tests to make sure that the number is greater than zero
				if (tetrisGridBackground[row][col] > 0) {
					if (row != 19 && tetrisGridBackground[row + 1][col] == 0) {

						tetrisGridBackground[row + 1][col] = tetrisGridBackground[row][col];
						tetrisGridBackground[row][col] = 0;

						// Tracks the last know position of the entire block
						blockPosition[counter][0] = row;
						blockPosition[counter][1] = col;

						counter++;
					} else {

						// If counter is not equal to 4 than the blocks the were shifted are shifted
						// back to their last know position
						if (counter != 4) {
							counter--;

							for (; counter >= 0; counter--) {
								tetrisGridBackground[blockPosition[counter][0]][blockPosition[counter][1]] = tetrisGridBackground[blockPosition[counter][0]
										+ 1][blockPosition[counter][1]];
								tetrisGridBackground[blockPosition[counter][0] + 1][blockPosition[counter][1]] = 0;
							}
						}

						// Runs methods for locking the blocks in place, deleting rows, and creating a
						// new block
						DeleteRow();
						
						//tests if the game is supposed to end or not
						if (BlockToStationary()) {
							CreateBlock();
							return 1;
						}else {
							return 2;
						}
						
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Takes the in the rotation of left or right and finds the correct block to
	 * rotate
	 * 
	 * @param rotateRightLeft
	 */
	public void RotateBlockKey(boolean rotateRightLeft) {
		boolean rotateCode = rotateRightLeft;
		int counter = 0;

		for (int row = tetrisGridBackground.length - 1; row >= 0; row--) {
			for (int col = tetrisGridBackground[row].length - 1; col >= 0; col--) {
				if (tetrisGridBackground[row][col] > 0) {
					blockPosition[counter][0] = row;
					blockPosition[counter][1] = col;
					counter++;
				}
			}
		}

		switch (blockKeyCode) {
		case 1:
			RotateLineBlock(rotateCode);
			break;
		case 2:
			RotateTBlock(rotateCode);
			break;
		case 3:
			RotateRightLBlock(rotateCode);
			break;
		case 4:
			RotateLeftLBlock(rotateCode);
			break;
		case 5:
			RotateRightZBlock(rotateCode);
			break;
		case 6:
			RotateLeftZBlock(rotateCode);
			break;
		default:
			break;
		}
	}

	/**
	 * Allows the Straight Line block to be rotated
	 * 
	 * @param rotateCode
	 */
	public void RotateLineBlock(boolean rotateCode) {

		// Temporary bounds in order to detect other blocks in the way of the rotation
		int tempBound1 = -1;
		int tempBound2 = tetrisGridBackground.length;

		// Detects if the block should be rotated up or flat
		if (rotateCode) {
			rotateDirectionCode++;
		} else {
			rotateDirectionCode--;

		}

		// Rotates the block vertically
		if (rotateDirectionCode % 2 == 0) {

			// 2 for loops that detect the other blocks in the game board in relation to the
			// current line block
			for (int index = blockPosition[2][0] - 1; index >= 0; index--) {
				if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
					tempBound1 = index;
					break;
				}
			}
			for (int index = blockPosition[2][0] + 1; index < tetrisGridBackground.length; index++) {
				if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
					tempBound2 = index;
					break;
				}
			}

			// Tests if the area is too small for the block to rotate vertically
			if ((tempBound2 - tempBound1 < 5) || (tempBound2 + 1 < 5)
					|| (tetrisGridBackground.length - tempBound1 < 5)) {
				rotateDirectionCode--;
			} else {

				// Removes the previous instance of the block from the board so the new rotated
				// one can be placed
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}
				blockPosition[0][0] -= 2;
				blockPosition[0][1] -= 2;
				blockPosition[1][0] -= 1;
				blockPosition[1][1] -= 1;
				blockPosition[3][0] += 1;
				blockPosition[3][1] += 1;

				// Checks for the board or other blocks being in the way and shifts the block
				// accordingly
				if (blockPosition[1][0] < 0 || tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] < 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][0] += 2;
					}
				} else if (blockPosition[0][0] < 0
						|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] < 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][0] += 1;
					}
				} else if (blockPosition[3][0] > 19
						|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] < 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][0] -= 1;
					}
				}

				// Places the new rotated block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 1;
				}
			}

			// Rotates the block horizontally
		} else {
			// Changes tempBound2 variable in order to detect far right column
			tempBound2 = tetrisGridBackground[0].length;

			// 2 for loops that detect the other blocks in the game board in relation to the
			// current line block
			for (int index = blockPosition[1][1] - 1; index >= 0; index--) {
				if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
					tempBound1 = index;
					break;
				}
			}
			for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
				if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
					tempBound2 = index;
					break;
				}
			}

			// Tests if the area is too small for the block to rotate horizontally
			if ((tempBound2 - tempBound1 < 5) || (tempBound2 + 1 < 5)
					|| (tetrisGridBackground[0].length - tempBound1 < 5)) {
				rotateDirectionCode++;
			} else {

				// Removes the previous instance of the block from the board so the new rotated
				// one can be placed
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}
				blockPosition[0][0] -= 1;
				blockPosition[0][1] -= 1;
				blockPosition[2][0] += 1;
				blockPosition[2][1] += 1;
				blockPosition[3][0] += 2;
				blockPosition[3][1] += 2;

				// Checks for the board or other blocks being in the way and shifts the block
				// accordingly
				if (blockPosition[2][1] > 9 || tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] < 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][1] -= 2;
					}
				} else if (blockPosition[3][1] > 9
						|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] < 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][1] -= 1;
					}
				} else if (blockPosition[0][1] < 0
						|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] < 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][1] += 1;
					}
				}

				// Places the new rotated block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 1;
				}
			}
		}
	}

	/**
	 * Allows the T block to be rotated
	 * 
	 * @param rotateCode
	 */
	public void RotateTBlock(boolean rotateCode) {

		// Temporary bounds in order to detect other blocks in the way of the rotation
		int tempBound1 = -1;
		int tempBound2 = tetrisGridBackground.length;

		// Detects the direction that the block should be rotated
		if (rotateCode) {
			rotateDirectionCode++;
			if (rotateDirectionCode == 5)
				rotateDirectionCode = 1;
		} else {
			rotateDirectionCode--;
			if (rotateDirectionCode == 0)
				rotateDirectionCode = 4;
		}

		// Rotates the block to the left position
		if (rotateDirectionCode == 4) {

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[1][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[2][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][0] + 2; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4)
					|| (tetrisGridBackground.length - tempBound1 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;
				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Changes the block position from the last orientation of the block
				if (rotateCode) {
					blockPosition[0][0] += 1;
					blockPosition[0][1] -= 1;

					if (blockPosition[0][0] > 19
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					}
				} else {
					blockPosition[1][0] -= 1;
					blockPosition[1][1] -= 1;

					if (blockPosition[1][0] < 0
							|| tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					}
				}

				// Places the new rotated block in the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 2;
				}
			}

			// Rotates the block in the up position
		} else if (rotateDirectionCode == 3) {

			// Changes the tempBound2 variable to check horizontal part of the board
			tempBound2 = tetrisGridBackground[0].length;

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[2][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][1] + 2; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[1][1] - 2; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4)
					|| (tetrisGridBackground[0].length - tempBound1 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;

				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}

			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Changes the block position from the last orientation of the block
				if (rotateCode) {
					blockPosition[0][0] -= 1;
					blockPosition[0][1] -= 1;

					if (blockPosition[0][1] < 0
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					}
				} else {
					blockPosition[0][0] -= 1;
					blockPosition[0][1] += 1;

					if (blockPosition[0][1] > 9
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					}
				}

				// Places the new rotated block in the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 2;
				}
			}

			// Rotates the block to the right position
		} else if (rotateDirectionCode == 2) {

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[2][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][0] + 2; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[1][0] - 2; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4)
					|| (tetrisGridBackground.length - tempBound1 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;
				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}

			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Changes the block position from the last orientation of the block
				if (rotateCode) {
					blockPosition[3][0] -= 1;
					blockPosition[3][1] += 1;

					if (blockPosition[3][0] < 0
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					}
				} else {
					blockPosition[2][0] += 1;
					blockPosition[2][1] += 1;

					if (blockPosition[2][0] > 19
							|| tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					}
				}

				// Places the new rotated block in the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 2;
				}
			}

			// Changes the block to the down position
		} else {

			// Changes the tempBound2 variable to check horizontal part of the board
			tempBound2 = tetrisGridBackground[0].length;

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[1][1] - 2; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[2][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][1] + 2; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4)
					|| (tetrisGridBackground[0].length - tempBound1 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;

				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Changes the block position from the last orientation of the block
				if (rotateCode) {
					blockPosition[3][0] += 1;
					blockPosition[3][1] += 1;

					if (blockPosition[3][1] > 9
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					}
				} else {
					blockPosition[3][0] += 1;
					blockPosition[3][1] -= 1;

					if (blockPosition[3][1] < 0
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					}
				}

				// Places the new rotated block in the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 2;
				}
			}

		}
	}

	/**
	 * Allows the Right L block to be rotated
	 * 
	 * @param rotateCode
	 */
	public void RotateRightLBlock(boolean rotateCode) {

		// Temporary bounds in order to detect other blocks in the way of the rotation
		// extra tempBound3 is added to account for the bend in the L
		int tempBound1 = -1;
		int tempBound2 = tetrisGridBackground.length;
		int tempBound3 = tetrisGridBackground.length;

		// Detects the direction that the block should be rotated
		if (rotateCode) {
			rotateDirectionCode++;
			if (rotateDirectionCode == 5)
				rotateDirectionCode = 1;
		} else {
			rotateDirectionCode--;
			if (rotateDirectionCode == 0)
				rotateDirectionCode = 4;
		}

		// Rotates block to the down position
		if (rotateDirectionCode == 4) {

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[2][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[2][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[2][1] + 1] != 0) {
						tempBound3 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[1][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[1][1] + 1] != 0) {
						tempBound3 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4) || (tetrisGridBackground.length - tempBound1 < 4)
					|| (tempBound3 - tempBound1 < 4) || (tempBound3 + 1 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;

				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Changes the block position from the last orientation of the block
				if (rotateCode) {
					blockPosition[0][1] += 2;
					blockPosition[3][0] += 1;
					blockPosition[3][1] += 1;
					blockPosition[1][0] -= 1;
					blockPosition[1][1] -= 1;

					if (blockPosition[0][0] < 0
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					} else if (tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					} else if (blockPosition[1][0] < 0
							|| tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					}

				} else {
					blockPosition[3][0] += 2;
					blockPosition[0][0] += 1;
					blockPosition[0][1] -= 1;
					blockPosition[2][0] -= 1;
					blockPosition[2][1] += 1;

					if (blockPosition[3][0] > 19
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					} else if (blockPosition[0][0] > 19
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					} else if (blockPosition[2][0] < 0
							|| tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					}
				}

				// Places the new rotated block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 3;
				}
			}

			// Rotates block to the left position
		} else if (rotateDirectionCode == 3) {

			// Changes the tempBound2 and tempBound3 variable to check horizontal part of
			// the board
			tempBound2 = tetrisGridBackground[0].length;
			tempBound3 = -1;

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[1][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[1][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[1][0] + 1][index] != 0) {
						tempBound3 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[2][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[2][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[2][0] + 1][index] != 0) {
						tempBound3 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4)
					|| (tetrisGridBackground[0].length - tempBound1 < 4) || (tempBound2 - tempBound3 < 4)
					|| (tetrisGridBackground[0].length - tempBound3 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;

				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				if (rotateCode) {
					blockPosition[3][0] += 2;
					blockPosition[2][0] += 1;
					blockPosition[2][1] -= 1;
					blockPosition[0][0] -= 1;
					blockPosition[0][1] += 1;

					if (blockPosition[3][1] < 0
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					} else if (blockPosition[2][1] < 0
							|| tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					} else if (blockPosition[0][1] > 9
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					}

				} else {
					blockPosition[0][1] -= 2;
					blockPosition[1][0] -= 1;
					blockPosition[1][1] -= 1;
					blockPosition[3][0] += 1;
					blockPosition[3][1] += 1;

					if (blockPosition[0][1] < 0
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					} else if (blockPosition[1][1] < 0
							|| tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					} else if (blockPosition[3][1] > 9
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					}
				}

				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 3;
				}
			}

			// Rotates block to the up position
		} else if (rotateDirectionCode == 2) {

			// tempBound3 is changed due to the moved bend in the L
			tempBound3 = -1;

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[1][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[1][1] - 1] != 0) {
						tempBound3 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[1][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[1][1] - 1] != 0) {
						tempBound3 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4) || (tetrisGridBackground.length - tempBound1 < 4)
					|| (tempBound2 - tempBound3 < 4) || (tetrisGridBackground.length - tempBound3 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;

				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Rotates the block based on the previous location
				if (rotateCode) {
					blockPosition[3][1] -= 2;
					blockPosition[0][0] -= 1;
					blockPosition[0][1] -= 1;
					blockPosition[2][0] += 1;
					blockPosition[2][1] += 1;

					if (blockPosition[3][0] < 0
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					} else if (blockPosition[0][0] < 0
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					} else if (blockPosition[2][0] > 19
							|| tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					}

				} else {
					blockPosition[0][0] -= 2;
					blockPosition[3][0] -= 1;
					blockPosition[3][1] += 1;
					blockPosition[1][0] += 1;
					blockPosition[1][1] -= 1;

					if (blockPosition[0][0] < 0
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					} else if (blockPosition[3][0] < 0
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					} else if (blockPosition[1][0] > 19
							|| tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					}
				}

				// Places the new block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 3;
				}
			}

			// Rotates block to the right position
		} else {

			// Changes the tempBound2 and tempBound3 variable to check horizontal part of
			// the board
			tempBound2 = tetrisGridBackground[0].length;
			tempBound3 = tetrisGridBackground[0].length;

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[2][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[2][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[2][0] - 1][index] != 0) {
						tempBound3 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[1][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[1][0] - 1][index] != 0) {
						tempBound3 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4)
					|| (tetrisGridBackground[0].length - tempBound1 < 4) || (tempBound3 - tempBound1 < 4)
					|| (tempBound3 + 1 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;

				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Rotates the block based on the previous location
				if (rotateCode) {
					blockPosition[0][0] -= 2;
					blockPosition[1][0] -= 1;
					blockPosition[1][1] += 1;
					blockPosition[3][0] += 1;
					blockPosition[3][1] -= 1;

					if (blockPosition[0][1] > 9
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					} else if (blockPosition[1][1] > 9
							|| tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					} else if (blockPosition[3][1] < 0
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					}

				} else {
					blockPosition[3][1] += 2;
					blockPosition[2][0] += 1;
					blockPosition[2][1] += 1;
					blockPosition[0][0] -= 1;
					blockPosition[0][1] -= 1;

					if (blockPosition[3][1] > 9
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					} else if (blockPosition[2][1] > 9
							|| tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					} else if (blockPosition[0][1] < 0
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					}
				}

				// Places the new block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 3;
				}
			}
		}
	}

	/**
	 * Allows the Left L block to be rotated
	 * 
	 * @param rotateCode
	 */
	public void RotateLeftLBlock(boolean rotateCode) {

		// Temporary bounds in order to detect other blocks in the way of the rotation
		// extra tempBound3 is added to account for the bend in the L
		int tempBound1 = -1;
		int tempBound2 = tetrisGridBackground.length;
		int tempBound3 = tetrisGridBackground.length;

		// Detects the direction that the block should be rotated
		if (rotateCode) {
			rotateDirectionCode++;
			if (rotateDirectionCode == 5)
				rotateDirectionCode = 1;
		} else {
			rotateDirectionCode--;
			if (rotateDirectionCode == 0)
				rotateDirectionCode = 4;
		}

		// Rotates the block to the up position
		if (rotateDirectionCode == 4) {

			// Changes the tempBound3 variable to check the top part of
			// the board
			tempBound3 = -1;

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[2][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[2][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[2][1] + 1] != 0) {
						tempBound3 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[1][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[1][1] + 1] != 0) {
						tempBound3 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4) || (tetrisGridBackground.length - tempBound1 < 4)
					|| (tempBound2 - tempBound3 < 4) || (tetrisGridBackground.length - tempBound3 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;

				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Rotates the block based on the previous location
				if (rotateCode) {
					blockPosition[0][0] -= 2;
					blockPosition[1][0] -= 1;
					blockPosition[1][1] -= 1;
					blockPosition[3][0] += 1;
					blockPosition[3][1] += 1;

					if (blockPosition[0][0] < 0
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					} else if (blockPosition[1][0] < 0
							|| tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					} else if (blockPosition[3][0] > 19
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					}

				} else {
					blockPosition[3][1] += 2;
					blockPosition[2][0] -= 1;
					blockPosition[2][1] += 1;
					blockPosition[0][0] += 1;
					blockPosition[0][1] -= 1;

					if (blockPosition[3][0] < 0
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					} else if (blockPosition[2][0] < 0
							|| tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					} else if (blockPosition[0][0] > 19
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					}
				}

				// Places the new block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 4;
				}
			}

			// Rotates the block to the right position
		} else if (rotateDirectionCode == 3) {

			// Changes the tempBound 2 and tempBound3 variable to check the horizontal part
			// of
			// the board
			tempBound2 = tetrisGridBackground[0].length;
			tempBound3 = tetrisGridBackground[0].length;

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[2][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[2][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[2][0] + 1][index] != 0) {
						tempBound3 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[1][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[1][0] + 1][index] != 0) {
						tempBound3 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4)
					|| (tetrisGridBackground[0].length - tempBound1 < 4) || (tempBound3 - tempBound1 < 4)
					|| (tempBound3 + 1 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;

				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Rotates the block based on the previous location
				if (rotateCode) {
					blockPosition[1][1] += 2;
					blockPosition[0][0] -= 1;
					blockPosition[0][1] += 1;
					blockPosition[3][0] += 1;
					blockPosition[3][1] -= 1;

					if (blockPosition[1][1] > 9
							|| tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					} else if (blockPosition[0][1] > 9
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					} else if (blockPosition[3][1] < 0
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					}
				} else {
					blockPosition[2][0] += 2;
					blockPosition[3][0] += 1;
					blockPosition[3][1] += 1;
					blockPosition[0][0] -= 1;
					blockPosition[0][1] -= 1;

					if (blockPosition[2][1] > 9
							|| tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					} else if (blockPosition[3][1] > 9
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					} else if (blockPosition[0][1] < 0
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					}
				}

				// Places the new block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 4;
				}
			}

			// Rotates the block to the down position
		} else if (rotateDirectionCode == 2) {

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[1][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[1][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[1][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[1][1] - 1] != 0) {
						tempBound3 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[2][0] - 1; index >= 0; index--) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[2][0] + 1; index < tetrisGridBackground.length; index++) {
					if (tetrisGridBackground[index][blockPosition[2][1] - 1] != 0) {
						tempBound3 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4) || (tetrisGridBackground.length - tempBound1 < 4)
					|| (tempBound3 - tempBound1 < 4) || (tempBound3 + 1 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;

				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Rotates the block based on the previous location
				if (rotateCode) {
					blockPosition[3][0] += 2;
					blockPosition[2][0] += 1;
					blockPosition[2][1] += 1;
					blockPosition[0][0] -= 1;
					blockPosition[0][1] -= 1;

					if (blockPosition[3][0] > 19
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					} else if (blockPosition[2][0] > 19
							|| tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					} else if (blockPosition[0][0] < 0
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					}
				} else {
					blockPosition[0][1] -= 2;
					blockPosition[1][0] += 1;
					blockPosition[1][1] -= 1;
					blockPosition[3][0] -= 1;
					blockPosition[3][1] += 1;

					if (blockPosition[0][0] > 19
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					} else if (blockPosition[1][0] > 19
							|| tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] -= 1;
						}
					} else if (blockPosition[3][0] < 0
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][0] += 1;
						}
					}
				}

				// Places the new block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 4;
				}
			}

			// Rotates the block to the left position
		} else {

			// Changes the tempBound 2 and tempBound3 variable to check the horizontal part
			// of
			// the board
			tempBound2 = tetrisGridBackground[0].length;
			tempBound3 = -1;

			// Checks the position of the block according to the direction it's rotating
			// from
			if (rotateCode) {
				for (int index = blockPosition[1][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[1][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[1][0] - 1][index] != 0) {
						tempBound3 = index;
						break;
					}
				}
			} else {
				for (int index = blockPosition[2][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound1 = index;
						break;
					}
				}
				for (int index = blockPosition[2][1] + 1; index < tetrisGridBackground[0].length; index++) {
					if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
						tempBound2 = index;
						break;
					}
				}
				for (int index = blockPosition[2][1] - 1; index >= 0; index--) {
					if (tetrisGridBackground[blockPosition[2][0] - 1][index] != 0) {
						tempBound3 = index;
						break;
					}
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4)
					|| (tetrisGridBackground[0].length - tempBound1 < 4) || (tempBound2 - tempBound3 < 4)
					|| (tetrisGridBackground[0].length - tempBound3 < 4)) {
				if (rotateCode) {
					rotateDirectionCode--;
					if (rotateDirectionCode == 0)
						rotateDirectionCode = 4;

				} else {
					rotateDirectionCode++;
					if (rotateDirectionCode == 5)
						rotateDirectionCode = 1;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Rotates the block based on the previous location
				if (rotateCode) {
					blockPosition[2][1] -= 2;
					blockPosition[3][0] += 1;
					blockPosition[3][1] -= 1;
					blockPosition[0][0] -= 1;
					blockPosition[0][1] += 1;

					if (blockPosition[2][1] < 0
							|| tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					} else if (blockPosition[3][0] < 0
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					} else if (blockPosition[0][1] > 9
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					}
				} else {
					blockPosition[1][0] -= 2;
					blockPosition[0][0] -= 1;
					blockPosition[0][1] -= 1;
					blockPosition[3][0] += 1;
					blockPosition[3][1] += 1;

					if (blockPosition[1][1] < 0
							|| tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					} else if (blockPosition[0][0] < 0
							|| tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] += 1;
						}
					} else if (blockPosition[3][1] > 9
							|| tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
						for (int index = 0; index < blockPosition.length; index++) {
							blockPosition[index][1] -= 1;
						}
					}
				}

				// Places the new block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 4;
				}
			}
		}
	}

	/**
	 * Allows the Right Z block to be rotated
	 * 
	 * @param rotateCode
	 */
	public void RotateRightZBlock(boolean rotateCode) {

		// Temporary bounds in order to detect other blocks in the way of the rotation
		// tempBound3 is to check extra position due to block being jagged
		int tempBound1 = -1;
		int tempBound2 = tetrisGridBackground.length;
		int tempBound3 = tetrisGridBackground.length;

		// Detects the direction that the block should be rotated
		if (rotateCode) {
			rotateDirectionCode++;
		} else {
			rotateDirectionCode--;
		}

		if (rotateDirectionCode % 2 == 0) {

			// Checks the position of the block according to the direction it's rotating
			// from
			for (int index = blockPosition[3][0]; index >= 0; index--) {
				if (tetrisGridBackground[index][blockPosition[3][1] - 1] != 0) {
					tempBound1 = index;
					break;
				}
			}
			for (int index = blockPosition[3][0] + 2; index < tetrisGridBackground.length; index++) {
				if (tetrisGridBackground[index][blockPosition[3][1]] != 0) {
					tempBound2 = index;
					break;
				}
			}
			for (int index = blockPosition[3][0] + 2; index < tetrisGridBackground.length; index++) {
				if (tetrisGridBackground[index][blockPosition[3][1] - 1] != 0) {
					tempBound3 = index;
					break;
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4) || (tetrisGridBackground.length - tempBound1 < 4)
					|| (tempBound3 - tempBound1 < 3)) {
				if (rotateCode) {
					rotateDirectionCode--;

				} else {
					rotateDirectionCode++;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Rotates the block to the vertical position
				blockPosition[1][0] -= 2;
				blockPosition[2][1] -= 2;

				// Shifts block if new rotation is out of bounds or another block is in the
				// current position
				if (blockPosition[1][0] < 0 || tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][0] += 1;
					}
				} else if (tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][0] += 2;
					}
				}

				// Places the new block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 5;
				}
			}

		} else {

			// Changes tempBound2 in order to check the horizontal position
			tempBound2 = tetrisGridBackground[0].length;
			tempBound3 = tetrisGridBackground[0].length;

			// Checks the position of the block according to the direction it's rotating
			// from
			for (int index = blockPosition[1][1] - 1; index >= 0; index--) {
				if (tetrisGridBackground[blockPosition[1][0] + 1][index] != 0) {
					tempBound1 = index;
					break;
				}
			}
			for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
				if (tetrisGridBackground[blockPosition[1][0]][index] != 0) {
					tempBound2 = index;
					break;
				}
			}
			for (int index = blockPosition[1][1] + 1; index < tetrisGridBackground[0].length; index++) {
				if (tetrisGridBackground[blockPosition[1][0] + 1][index] != 0) {
					tempBound3 = index;
					break;
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4)
					|| (tetrisGridBackground[0].length - tempBound1 < 4) || (tempBound3 - tempBound1 < 3)) {
				if (rotateCode) {
					rotateDirectionCode--;

				} else {
					rotateDirectionCode++;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Rotates the block to the vertical position
				blockPosition[3][0] += 2;
				blockPosition[2][1] += 2;

				if (blockPosition[2][1] > 9 || tetrisGridBackground[blockPosition[2][0]][blockPosition[2][1]] != 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][1] -= 1;
					}
				} else if (tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][1] += 1;
					}
				}

				// Places the new block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 5;
				}
			}
		}

	}

	/**
	 * Allows the Left Z block to be rotated
	 * 
	 * @param rotateCode
	 */
	public void RotateLeftZBlock(boolean rotateCode) {

		// Temporary bounds in order to detect other blocks in the way of the rotation
		// tempBound3 is to check extra position due to block being jagged
		int tempBound1 = -1;
		int tempBound2 = tetrisGridBackground.length;
		int tempBound3 = tetrisGridBackground.length;

		// Detects the direction that the block should be rotated
		if (rotateCode) {
			rotateDirectionCode++;
		} else {
			rotateDirectionCode--;
		}

		if (rotateDirectionCode % 2 == 0) {

			// Checks the position of the block according to the direction it's rotating
			// from
			for (int index = blockPosition[2][0]; index >= 0; index--) {
				if (tetrisGridBackground[index][blockPosition[2][1] + 1] != 0) {
					tempBound1 = index;
					break;
				}
			}
			for (int index = blockPosition[2][0] + 2; index < tetrisGridBackground.length; index++) {
				if (tetrisGridBackground[index][blockPosition[2][1]] != 0) {
					tempBound2 = index;
					break;
				}
			}
			for (int index = blockPosition[2][0] + 2; index < tetrisGridBackground.length; index++) {
				if (tetrisGridBackground[index][blockPosition[2][1] + 1] != 0) {
					tempBound3 = index;
					break;
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4) || (tetrisGridBackground.length - tempBound1 < 4)
					|| (tempBound3 - tempBound1 < 3)) {
				if (rotateCode) {
					rotateDirectionCode--;

				} else {
					rotateDirectionCode++;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Changes the position for the new block
				blockPosition[0][0] -= 2;
				blockPosition[3][1] += 2;

				// Shifts block if new rotation is out of bounds or another block is in the
				// current position
				if (blockPosition[0][0] < 0 || tetrisGridBackground[blockPosition[0][0]][blockPosition[0][1]] != 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][0] += 1;
					}
				} else if (tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][0] += 2;
					}
				}

				// Places the new block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 6;
				}
			}

		} else {

			// Changes tempBound2 in order to check the horizontal position
			tempBound2 = tetrisGridBackground[0].length;
			tempBound3 = -1;

			// Checks the position of the block according to the direction it's rotating
			// from
			for (int index = blockPosition[2][1] - 1; index >= 0; index--) {
				if (tetrisGridBackground[blockPosition[2][0]][index] != 0) {
					tempBound1 = index;
					break;
				}
			}
			for (int index = blockPosition[2][1] + 1; index < tetrisGridBackground[0].length; index++) {
				if (tetrisGridBackground[blockPosition[2][0] + 1][index] != 0) {
					tempBound2 = index;
					break;
				}
			}
			for (int index = blockPosition[2][1] - 1; index >= 0; index--) {
				if (tetrisGridBackground[blockPosition[2][0] + 1][index] != 0) {
					tempBound3 = index;
					break;
				}
			}

			// Tests if the area is too small for the block to rotate
			if ((tempBound2 - tempBound1 < 4) || (tempBound2 + 1 < 4)
					|| (tetrisGridBackground[0].length - tempBound1 < 4) || (tempBound2 - tempBound3 < 3)) {
				if (rotateCode) {
					rotateDirectionCode--;

				} else {
					rotateDirectionCode++;
				}
			} else {

				// Removes the previous instance of the block
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 0;
				}

				// Changes the position for the new block
				blockPosition[3][0] += 2;
				blockPosition[1][1] -= 2;

				// Shifts block if new rotation is out of bounds or another block is in the
				// current position
				if (blockPosition[1][1] < 0 || tetrisGridBackground[blockPosition[1][0]][blockPosition[1][1]] != 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][1] += 1;
					}
				} else if (tetrisGridBackground[blockPosition[3][0]][blockPosition[3][1]] != 0) {
					for (int index = 0; index < blockPosition.length; index++) {
						blockPosition[index][1] -= 1;
					}
				}

				// Places the new block into the game board
				for (int index = 0; index < blockPosition.length; index++) {
					tetrisGridBackground[blockPosition[index][0]][blockPosition[index][1]] = 6;
				}
			}
		}

	}

	/**
	 * Randomly generates a key code for the block to create and creates a block
	 * according to those specifications.
	 */
	public void CreateBlock() {
		blockKeyCode = nextBlockKeyCode;
		rotateDirectionCode = 1;

		switch (blockKeyCode) {
		// Generates a line block
		case 1:
			tetrisGridBackground[0][3] = blockKeyCode;
			tetrisGridBackground[0][4] = blockKeyCode;
			tetrisGridBackground[0][5] = blockKeyCode;
			tetrisGridBackground[0][6] = blockKeyCode;
			break;
		// Generates a T block
		case 2:
			tetrisGridBackground[0][3] = blockKeyCode;
			tetrisGridBackground[0][4] = blockKeyCode;
			tetrisGridBackground[0][5] = blockKeyCode;
			tetrisGridBackground[1][4] = blockKeyCode;
			break;
		// Generates a Right L block
		case 3:
			tetrisGridBackground[1][3] = blockKeyCode;
			tetrisGridBackground[1][4] = blockKeyCode;
			tetrisGridBackground[1][5] = blockKeyCode;
			tetrisGridBackground[0][5] = blockKeyCode;
			break;
		// Generates a Left L block
		case 4:
			tetrisGridBackground[1][3] = blockKeyCode;
			tetrisGridBackground[1][4] = blockKeyCode;
			tetrisGridBackground[1][5] = blockKeyCode;
			tetrisGridBackground[0][3] = blockKeyCode;
			break;
		// Generates a Right Z block
		case 5:
			tetrisGridBackground[1][3] = blockKeyCode;
			tetrisGridBackground[1][4] = blockKeyCode;
			tetrisGridBackground[0][4] = blockKeyCode;
			tetrisGridBackground[0][5] = blockKeyCode;
			break;
		// Generates a Left Z block
		case 6:
			tetrisGridBackground[0][3] = blockKeyCode;
			tetrisGridBackground[1][4] = blockKeyCode;
			tetrisGridBackground[1][5] = blockKeyCode;
			tetrisGridBackground[0][4] = blockKeyCode;
			break;
		// Generates a Square block
		case 7:
			tetrisGridBackground[0][4] = blockKeyCode;
			tetrisGridBackground[0][5] = blockKeyCode;
			tetrisGridBackground[1][5] = blockKeyCode;
			tetrisGridBackground[1][4] = blockKeyCode;
			break;
		}
		DeleteCurrentNextBlock();
		GenerateNextBlock();
	}

	/**
	 * Generates a block for the next block section in the game
	 */
	public void GenerateNextBlock() {
		randomGen = new Random();
		nextBlockKeyCode = randomGen.nextInt(7) + 1;

		switch (nextBlockKeyCode) {
		// Generates a line block
		case 1:
			nextBlockGridBackground[1][0] = nextBlockKeyCode;
			nextBlockGridBackground[1][1] = nextBlockKeyCode;
			nextBlockGridBackground[1][2] = nextBlockKeyCode;
			nextBlockGridBackground[1][3] = nextBlockKeyCode;
			break;
		// Generates a T block
		case 2:
			nextBlockGridBackground[0][0] = nextBlockKeyCode;
			nextBlockGridBackground[0][1] = nextBlockKeyCode;
			nextBlockGridBackground[0][2] = nextBlockKeyCode;
			nextBlockGridBackground[1][1] = nextBlockKeyCode;
			break;
		// Generates a Right L block
		case 3:
			nextBlockGridBackground[1][1] = nextBlockKeyCode;
			nextBlockGridBackground[1][2] = nextBlockKeyCode;
			nextBlockGridBackground[1][3] = nextBlockKeyCode;
			nextBlockGridBackground[0][3] = nextBlockKeyCode;
			break;
		// Generates a Left L block
		case 4:
			nextBlockGridBackground[1][0] = nextBlockKeyCode;
			nextBlockGridBackground[1][1] = nextBlockKeyCode;
			nextBlockGridBackground[1][2] = nextBlockKeyCode;
			nextBlockGridBackground[0][0] = nextBlockKeyCode;
			break;
		// Generates a Right Z block
		case 5:
			nextBlockGridBackground[1][0] = nextBlockKeyCode;
			nextBlockGridBackground[1][1] = nextBlockKeyCode;
			nextBlockGridBackground[0][1] = nextBlockKeyCode;
			nextBlockGridBackground[0][2] = nextBlockKeyCode;
			break;
		// Generates a Left Z block
		case 6:
			nextBlockGridBackground[1][3] = nextBlockKeyCode;
			nextBlockGridBackground[1][2] = nextBlockKeyCode;
			nextBlockGridBackground[0][2] = nextBlockKeyCode;
			nextBlockGridBackground[0][1] = nextBlockKeyCode;
			break;
		// Generates a Square block
		case 7:
			nextBlockGridBackground[1][1] = nextBlockKeyCode;
			nextBlockGridBackground[1][2] = nextBlockKeyCode;
			nextBlockGridBackground[0][1] = nextBlockKeyCode;
			nextBlockGridBackground[0][2] = nextBlockKeyCode;
			break;
		}
	}

	/**
	 * Removes the previous contents of the next block array
	 */
	public void DeleteCurrentNextBlock() {
		for (int row = 0; row < nextBlockGridBackground.length; row++) {
			for (int col = 0; col < nextBlockGridBackground[0].length; col++) {
				nextBlockGridBackground[row][col] = 0;
			}
		}
	}

	/**
	 * Changes the numbers on the board to negative in order to remove the movement
	 * of that block
	 */
	public boolean BlockToStationary() {
		for (int row = 0; row < tetrisGridBackground.length; row++) {
			for (int col = 0; col < tetrisGridBackground[row].length; col++) {
				if (tetrisGridBackground[row][col] > 0) {
					tetrisGridBackground[row][col] *= -1;
				}
			}
		}
		for (int index = 0; index < tetrisGridBackground[0].length; index++) {
			if (tetrisGridBackground[0][index] < 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Displays a text based grid for testing purposes
	 */
	public void displayGrid() {
		for (int row = 0; row < tetrisGridBackground.length; row++) {
			for (int col = 0; col < tetrisGridBackground[row].length; col++) {
				System.out.print(tetrisGridBackground[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
