package org.janus.actions;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.janus.data.Configurable;
import org.janus.data.ConfigurableHelper;
import org.janus.data.DataContext;
import org.janus.data.DataDescription;
import org.janus.helper.DebugAssistent;

public class DataValueList extends Vector<DataValue> implements Configurable {

	private static final long serialVersionUID = 1L;
	private DataDescription description;

	public DataValueList() {
		super();
	}

	@Override
	public void configure(DataDescription description) {
		DebugAssistent.doNullCheck(description);

		if (DescriptionIsNew(description)) {
			this.description = description;
			ConfigurableHelper.configure(description, this);
		}
	}

	private boolean DescriptionIsNew(DataDescription description) {
		return this.description == null
				|| !this.description.equals(description);
	}

	public void write(DataContext ctx, Writer writer, char trenner)
			throws IOException {
		DebugAssistent.doNullCheck(ctx, writer);

		for (DataValue d : this) {
			Object obj = d.getObject(ctx);
			String ausgabe = d.getFormat().format(obj);
			writer.write(trenner);
			writer.write(ausgabe);
		}
	}

	public void loadFromResultSet(DataContext ctx, ResultSet result)
			throws IOException, SQLException {
		DebugAssistent.doNullCheck(ctx, result);

		int index = 1;
		for (DataValue d : this) {
			Serializable obj = (Serializable)result.getObject(index);
			d.setObject(ctx, obj);
			index++;
		}
	}

	public void fillPreparedStatement(DataContext ctx, PreparedStatement stmt)
			throws IOException, SQLException {
		DebugAssistent.doNullCheck(ctx, stmt);

		int index = 1;
		for (DataValue d : this) {
			Object obj = d.getObject(ctx);
			stmt.setObject(index, obj);
			index++;
		}
	}

	public DataValueList addValue(DataValue value) {
		DebugAssistent.doNullCheck(value);

		if (description != null) {
			value.configure(description);
		}
		this.addElement(value);
		return this;
	}

}
