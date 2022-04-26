package io.kata.challenges;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;

public class RangeTest {

  @Test
  public void should_create_range() {
    Range<Integer> range = Range.open(5, 50);
    assertThat(range.lowerbound()).isEqualTo(5);
    assertThat(range.upperbound()).isEqualTo(50);
  }

  @Test
  public void should_throw_error__when_create_with_lowerbound_bigger_than_upperbound() {
	  try {
    
		  Range.open(500, 1);
		  fail("Should not allow creating a invalid Range");
    } catch(IllegalArgumentException e) {
    	  assertThat(e.getMessage().contains("lowerbound > upperbound")).isEqualTo(true);
	    }
  }

  @Test
  public void open_range() {
	  Range<Integer> open = Range.open(5, 7);
	  assertThat(open.contains(5)).isEqualTo(false);
	  assertThat(open.contains(10)).isEqualTo(false);
  }
  
  @Test
  public void close_range() {
	  Range<Integer> closed = Range.closed(5, 7);
	  assertThat(closed.contains(5)).isEqualTo(true);
  }
  
  @Test
  public void openClosed_range() {
	  Range<Integer> openClosed = Range.openClosed(5, 7);
	  assertThat(openClosed.contains(5)).isEqualTo(false);
	  assertThat(openClosed.contains(7)).isEqualTo(true);
  }
  
  @Test
  public void  generic_type_with_all_types() {
	  	Range<String> text = Range.open("abc", "xyz");
		
		assertThat(text.contains("aaa")).isEqualTo(false);
		assertThat(text.contains("abd")).isEqualTo(true);
		assertThat(text.contains("ayz")).isEqualTo(true);
		assertThat(text.contains("aba")).isEqualTo(false);
		assertThat(text.contains("xyz")).isEqualTo(false);
		
		//====================== OPEN ===================================

		Range<BigDecimal> decimals = Range.open(new BigDecimal("1.32432"),
				new BigDecimal("1.324323423423423423423"));
		assertThat(
				decimals.contains(new BigDecimal("1.324323423423"))).isEqualTo(true);
		assertThat(
				decimals.contains(new BigDecimal("1.3243234234234234234239999"))).isEqualTo(false);
		//========================== CLOSED ===================================
		
		Range<ChronoLocalDate> dates = Range.closed(
				LocalDate.of(2016, Month.SEPTEMBER, 11),
				LocalDate.of(2017, Month.JUNE, 30));
		assertThat(
				dates.contains(LocalDate.of(2016, Month.NOVEMBER, 11))).isEqualTo(true);
		
		//================== LESSTHAN ============================================
		
		Range<Integer> lessthan1 = Range.lessThan(10);
		Range<Integer> lessthan2 = Range.lessThan(10);

		assertThat(lessthan1.contains(-10)).isEqualTo(true);
		assertThat(lessthan2.contains(11)).isEqualTo(false);
		
		//================== GREATERTHAN ============================================

		
		Range<Integer> greater1 = Range.greaterThan(10);
		Range<Integer> greater2 = Range.greaterThan(10);
		
		assertThat(greater1.contains(100)).isEqualTo(true);
		assertThat(greater2.contains(-11)).isEqualTo(false);
		
		Range<ChronoLocalDate> afterEpoch = Range.greaterThan(LocalDate.of(1900, Month.JANUARY, 1));
		assertThat(afterEpoch.contains(LocalDate.of(2016, Month.JULY, 28))).isEqualTo(true); 
		assertThat(afterEpoch.contains(LocalDate.of(1750, Month.JANUARY, 1))).isEqualTo(false); 

		// ============================== ATLEAST ============================================//

		Range<Integer> atLeastFive = Range.atLeast(5);
		assertThat(atLeastFive.contains(5)).isEqualTo(true); 
		assertThat(atLeastFive.contains(4)).isEqualTo(false);

		// ============================== ATMOST ============================================//
		Range<Integer> atMostFive = Range.atMost(5);
		assertThat(atMostFive.contains(5)).isEqualTo(true);
		assertThat(atMostFive.contains(-234234)).isEqualTo(true);
		assertThat(atMostFive.contains(6)).isEqualTo(false); 
  }
  
  @Test
  public void closedOpen_range() {
	  Range<Integer> closedOpen = Range.closedOpen(5, 7);
	  assertThat(closedOpen.contains(5)).isEqualTo(true);
	  assertThat(closedOpen.contains(7)).isEqualTo(false);
  }
  
  @Test
  public void range_should_be_state_independent() {
    Range<Integer> range1 = Range.closedOpen(5, 11);
    Range<Integer> range2 = Range.closed(11, 20);

    assertThat(range1.contains(11)).isEqualTo(false);
    assertThat(range2.contains(11)).isEqualTo(true);
  }
  
  @Test
  public void range_toString() {
	  
	  Range<ChronoLocalDate> within2020 = Range.closed(
			  LocalDate.of(2020, Month.JANUARY, 1),
			  LocalDate.of(2020, Month.DECEMBER, 31)
			);
	  	  
	  String date = within2020.toString();
	  assertThat(date.equals("[2020-01-01,2020-12-31]")).isEqualTo(true);

  }
  
}
