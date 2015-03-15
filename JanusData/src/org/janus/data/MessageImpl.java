package org.janus.data;

import org.janus.helper.DebugAssistent;

/**
 * Teilt einen Text in von prefix und postfix abgeschlossene Teile auf
 * 
 * Ist z.B prefix = $( und postfix = ), so wird "Das ist ein $(test)" in
 * "Das ist ein ","text","" aufgeteilt
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 */
public class MessageImpl implements Message {
	public String prefix = "$(";
	public String postfix = ")";

	private int lengthPre = 0;
	private int lengthPost = 0;

	/**
	 * Aufgeteilter Text
	 */
	protected String[] texte = null;

	/**
	 * Schlüsselwerte in einen DataContext
	 * 
	 * @see DataContext
	 */
	private int[] handles = null;

	public static String[] splitText(String text, String prefix, String postfix) {
		MessageImpl message = new MessageImpl(text, postfix, postfix);
		return message.splitText(text);
	}

	public MessageImpl(String text) {
		this(text, "$(", ")");
	}

	public MessageImpl(String text, String prefix, String postfix) {
		DebugAssistent.doNullCheck(text);

		this.prefix = prefix;
		this.postfix = postfix;
		lengthPre = prefix.length();
		lengthPost = postfix.length();
		setMessage(text);
	}

	public void setMessage(String text) {
		texte = splitText(text);
	}

	private String[] splitText(String text) {
		String[] texte = new String[calculateArraySize(text)];
		int pos = 0;
		int i = 0;
		int opos = -1;
		opos = pos;
		while (pos >= 0 || i == 0) {
			pos = text.indexOf(prefix, opos);
			if (pos >= 0) {
				texte[i] = text.substring(opos, pos);
				i++;
				opos = pos + lengthPre;
				pos = text.indexOf(postfix, opos);
				texte[i] = text.substring(opos, pos);
				opos = pos + lengthPost;
				i++;
			} else {
				texte[i] = text.substring(opos);
				i++;
			}
		}
		return texte;
	}

	/**
	 * /** Eigentliche Aufteilfunktion
	 * 
	 * @param text
	 *            Text der aufgetelt werden soll
	 * @param prefix
	 *            Trenner
	 * @param postfix
	 *            Trenner
	 * @return Aufgeteilter Text
	 * 
	 */
	private int calculateArraySize(String text) {
		int count = 0;
		int pos = 0;

		while (pos >= 0 || count == 0) {
			pos = text.indexOf(prefix, pos);
			if (pos >= 0) {
				pos = calculatePositionAfterNextPostfix(text, pos);
				count += 2;
			} else {
				count++;
			}
		}
		return count;
	}

	private int calculatePositionAfterNextPostfix(String text, int pos) {
		return text.indexOf(postfix, pos + lengthPre) + lengthPost;
	}

	/*
	 * Holt die Schlüsselwerte
	 * 
	 * @param model
	 */
	public void initHandles(DataDescription model) {
		int anz = texte.length;
		if (anz > 1) {
			anz--;
			anz = anz / 2;
			handles = new int[anz];
			for (int i = 0; i < anz; i++) {
				handles[i] = model.getHandle(texte[2 * i + 1]);
			}
		}
	}

	/**
	 * Füllt den Text mit den Werte im DataContext
	 * 
	 */
	@Override
	public String getMessage(DataContext ctx) {
		DebugAssistent.doNullCheck(ctx);

		StringBuffer buffer = new StringBuffer();
		initHandles(ctx.getDataDescription());

		int anz = texte.length;
		if (anz == 1) {
			buffer.append(texte[0]);
		} else {
			anz--;
			anz = anz / 2;
			for (int i = 0; i < anz; i++) {
				buffer.append(texte[2 * i]);
				System.out.println(handles[i]);
				Object obj = ctx.getObject(handles[i]);
				if (obj != null) {
					buffer.append(obj.toString());
				}
			}
			buffer.append(texte[texte.length - 1]);
		}
		return buffer.toString();
	}

	public String[] getTexte() {
		return texte;
	}

	public int[] getHandles() {
		return handles;
	}

}
