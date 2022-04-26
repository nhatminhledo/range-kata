package io.kata.challenges;

/**
 * Extends the Range such that it can supports any types implementing Comparable interface
 * */

public class Range<T extends Comparable<T>> {

  private T lowerbound;
  private T upperbound;
  private BoundType type;

  /**
   * Constructor is private BY DESIGN.
   */
  private Range(T lower , T upper , BoundType type) {
      if(lower != null && upper != null && lower.compareTo(upper) >= 0){
          throw new IllegalArgumentException("not allowed to create a Range with lowerbound > upperbound");
      }
      this.lowerbound = lower;
      this.upperbound = upper;      
      this.type 	  = type;
  }

  /**
   * Returns {@code true} on if the given {@code value} is contained in this
   * {@code Range}.
   */
  public boolean contains(T obj) {
	  return  isOnBoundary(obj) || isInBoundary(obj);  
	  
  }
  
  /**
   * Creates a new <b>open</b> range excludes both bounds 
   */
  public static <T extends Comparable<T>> Range<T> open(T lower, T upper) {
		return new Range<T>(lower, upper, BoundType.OPEN);
	}
  
  /**
   * Creates a new <b>closed</b> {@code Range} that includes both bounds.
   */

  public static <T extends Comparable<T>> Range<T> closed(T lower, T upper) {
		return new Range<T>(lower, upper, BoundType.CLOSED);
	}

  /**
   * Creates a new <b>openClosed</b> excludes lowerbound but includes upperbound 
   */
  public static <T extends Comparable<T>> Range<T> openClosed(T lower, T upper) {
		return new Range<T>(lower, upper, BoundType.OPENCLOSED);
	}
  
  /**
   * Creates a new <b>closedOpen</b> includes lowerbound but excludes upperbound
   */
  public static <T extends Comparable<T>> Range<T> closedOpen(T lower, T upper) {
		return new Range<T>(lower, upper, BoundType.CLOSEDOPEN);
	}
  
  /**
   * Returns a range that contains all values strictly great than {@code endpoint}.
   *
   *
   */
  public static <T extends Comparable<T>> Range<T> greaterThan(T endpoint) {
    return new Range<T>(endpoint, null , BoundType.GREATERTHAN);
  }
  
  /**
   * Returns a range that contains all values strictly less than {@code endpoint}.
   *
   *
   */
  public static <T extends Comparable<T>> Range<T> lessThan(T endpoint) {
    return new Range<T>(null, endpoint, BoundType.LESSTHAN);
  }
  
  /**
   * Returns a range that contains all values strictly less than {@code endpoint}.
   *
   *
   */
  public static <T extends Comparable<T>> Range<T> atMost(T endpoint) {
    return new Range<T>(null, endpoint, BoundType.ATMOST);
  }
  
  /**
   * Returns a range that contains all values strictly at least {@code endpoint}.
   *
   *
   */
  public static <T extends Comparable<T>> Range<T> atLeast(T endpoint) {
    return new Range<T>(endpoint, null, BoundType.ATLEAST);
  }
	
  private boolean isOnBoundary(T i) {
	  
		switch (this.type) {
		case OPEN:
			return false;
		case CLOSED:
			return i.compareTo(this.lowerbound()) == 0 || i.compareTo(this.upperbound()) == 0;
		case OPENCLOSED:
			return i.compareTo(this.upperbound()) == 0;
		case CLOSEDOPEN:
			return i.compareTo(this.lowerbound()) == 0;
		case LESSTHAN:
			return i.compareTo(this.upperbound()) == -1;
		case GREATERTHAN:
			return i.compareTo(this.lowerbound()) >= 1 ? true : false;
		case ATLEAST:
			return i.compareTo(this.lowerbound()) == 0;
		case ATMOST:
			return i.compareTo(this.upperbound()) <= 0;	
		default:
			return false;
		}
	}
  
  private boolean isInBoundary(T obj) {
	  if(lowerbound == null || upperbound == null) return false;
	  return obj.compareTo(lowerbound) > 0 && obj.compareTo(upperbound) < 0; 
	}

  /**
   * Returns the {@code lowerbound} of this {@code Range}.
   */
  public T lowerbound() {
    return this.lowerbound;
  }

  /**
   * Returns the {@code upperbound} of this {@code Range}.
   */
  public T upperbound() {
    return this.upperbound;
  }
  
  @Override
  public String toString() {
    return convert(this.lowerbound(), this.upperbound());
  }
  

  private <T extends Comparable<T>> String convert(T lowerBound, T upperBound) {
	    StringBuilder sb = new StringBuilder(16);
	    appendCharacter(sb, lowerBound , upperBound);
	    return sb.toString();
	  }

 private <T extends Comparable<T>> String appendCharacter(StringBuilder sb, T lowerBound2, T upperBound2) {
	  
		switch (this.type) {
		case OPEN:
			 	sb.append('(').append(lowerBound2);
			    sb.append(",");
			    sb.append(upperBound2).append(')');
			return sb.toString();
		case CLOSED:
				sb.append('[').append(lowerBound2);
				sb.append(",");
				sb.append(upperBound2).append(']');
			return sb.toString();
		case OPENCLOSED:
				sb.append('(').append(lowerBound2);
				sb.append(",");
				sb.append(upperBound2).append(']');
			return sb.toString();
		case CLOSEDOPEN:
				sb.append('[').append(lowerBound2);
				sb.append(",");
				sb.append(upperBound2).append(')');
			return sb.toString();
		case LESSTHAN:
				sb.append('(').append("-∞");
				sb.append(",");
				sb.append(upperBound2).append(']');
			return sb.toString();
		case GREATERTHAN:
				sb.append('(').append(lowerBound2);
				sb.append(",");
				sb.append("+∞").append(')');
		return  sb.toString();
		case ATLEAST:
				sb.append('[').append(lowerBound2);
				sb.append(",");
				sb.append("+∞").append(')');
			return sb.toString();
		case ATMOST:
			sb.append('(').append("-∞");
			sb.append(",");
			sb.append(upperBound2).append(']');
		return sb.toString();
		default:
			return "";
		}
	}
  
  
/**
   * Types of Range.
   */
  enum BoundType {
		OPEN, CLOSED, OPENCLOSED, CLOSEDOPEN, LESSTHAN, GREATERTHAN, ATLEAST, ATMOST ;
	}
}
