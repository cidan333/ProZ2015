package Chess;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private final JPanel gui = new JPanel(new BorderLayout());

	private JPanel[][] chessBoardSquares = new JPanel[8][8];
	private JPanel chessBoard;
	private JTextArea tabelapgn;

	private static final String COLS = "ABCDEFGH";

	public MainFrame() throws FileNotFoundException, IOException {
		initializeBoard();
		initializepgnTable();
	}

	public final void initializepgnTable() throws FileNotFoundException,
			IOException {

		File file = new File("pgn.txt");
		String zdanie = new Scanner(file).useDelimiter("\\A").next();

		tabelapgn = new JTextArea(zdanie, 5, 5);
		tabelapgn.setEditable(false);
		tabelapgn.setBackground(Color.white);
		tabelapgn.setMinimumSize(tabelapgn.getSize());
		tabelapgn.setVisible(false);
		gui.add(tabelapgn, BorderLayout.EAST);

		JButton buttonSearch = new JButton("Szukaj");
		gui.add(buttonSearch, BorderLayout.PAGE_START);

	}

	public final void initializeBoard() {
		// set up the main GUI
		gui.setBorder(new EmptyBorder(10, 10, 10, 10));

		chessBoard = new JPanel(new GridLayout(0, 9));
		chessBoard.setBorder(new LineBorder(Color.DARK_GRAY));
		gui.add(chessBoard);

		for (int ii = 0; ii < chessBoardSquares.length; ii++) {
			for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
				JPanel b = new JPanel();

				if ((jj % 2 == 1 && ii % 2 == 1)
				// ) {
						|| (jj % 2 == 0 && ii % 2 == 0)) {
					b.setBackground(Color.WHITE);
				} else {
					b.setBackground(Color.DARK_GRAY);
				}
				chessBoardSquares[jj][ii] = b;
			}
		}
		// wype³nienie szachownicy

		chessBoardSquares[0][0].add(new JLabel(new ImageIcon(
				"figury/blackrook1.png")));
		chessBoardSquares[7][0].add(new JLabel(new ImageIcon(
				"figury/blackrook1.png")));
		chessBoardSquares[1][0].add(new JLabel(new ImageIcon(
				"figury/blackknight1.png")));
		chessBoardSquares[6][0].add(new JLabel(new ImageIcon(
				"figury/blackknight1.png")));
		chessBoardSquares[2][0].add(new JLabel(new ImageIcon(
				"figury/blackbishop1.png")));
		chessBoardSquares[5][0].add(new JLabel(new ImageIcon(
				"figury/blackbishop1.png")));
		chessBoardSquares[3][0].add(new JLabel(new ImageIcon(
				"figury/blackqueen1.png")));
		chessBoardSquares[4][0].add(new JLabel(new ImageIcon(
				"figury/blackking1.png")));

		for (int i = 0; i < 8; i++)
			chessBoardSquares[i][1].add(new JLabel(new ImageIcon(
					"figury/blackpawn1.png")));

		chessBoardSquares[0][7].add(new JLabel(new ImageIcon(
				"figury/whiterook1.png")));
		chessBoardSquares[7][7].add(new JLabel(new ImageIcon(
				"figury/whiterook1.png")));
		chessBoardSquares[1][7].add(new JLabel(new ImageIcon(
				"figury/whiteknight1.png")));
		chessBoardSquares[6][7].add(new JLabel(new ImageIcon(
				"figury/whiteknight1.png")));
		chessBoardSquares[2][7].add(new JLabel(new ImageIcon(
				"figury/whitebishop1.png")));
		chessBoardSquares[5][7].add(new JLabel(new ImageIcon(
				"figury/whitebishop1.png")));
		chessBoardSquares[3][7].add(new JLabel(new ImageIcon(
				"figury/whitequeen1.png")));
		chessBoardSquares[4][7].add(new JLabel(new ImageIcon(
				"figury/whiteking1.png")));

		for (int i = 0; i < 8; i++)
			chessBoardSquares[i][6].add(new JLabel(new ImageIcon(
					"figury/whitepawn1.png")));

		chessBoard.add(new JLabel(""));

		for (int ii = 0; ii < 8; ii++) {
			chessBoard.add(new JLabel(COLS.substring(ii, ii + 1),
					SwingConstants.CENTER));
		}

		for (int ii = 0; ii < 8; ii++) {
			for (int jj = 0; jj < 8; jj++) {
				switch (jj) {
				case 0:
					chessBoard.add(new JLabel("" + (8 - ii),
							SwingConstants.CENTER));
				default:
					chessBoard.add(chessBoardSquares[jj][ii]);
				}
			}
		}
	}

	public final JComponent getTable() {
		return tabelapgn;
	}

	public final JComponent getChessBoard() {
		return chessBoard;
	}

	public final JComponent getGui() {
		return gui;
	}

}
