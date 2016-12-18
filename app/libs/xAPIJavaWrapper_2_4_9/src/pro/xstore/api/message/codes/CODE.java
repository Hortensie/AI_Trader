package pro.xstore.api.message.codes;

public class CODE {

	private long code;

	public CODE(long code) {
		this.code = code;
	}

	public long getCode() {
		return code;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [code=" + code + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (code ^ (code >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CODE other = (CODE) obj;
		if (code != other.code)
			return false;
		return true;
	}
}