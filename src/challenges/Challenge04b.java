package challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Challenge04b implements IChallenge {

  @Override
  public String[] translate(String[] input) {
    List<TimeSlot> slots = new ArrayList<>();

    for (String s : input) {
      String dateSec = s.split("]")[0];
      String textSec = s.split("]")[1].substring(1);
      int year = Integer.valueOf(dateSec.substring(1, 5));
      int month = Integer.valueOf(dateSec.substring(6, 8));
      int day = Integer.valueOf(dateSec.substring(9, 11));
      int hour = Integer.valueOf(dateSec.substring(12, 14));
      int minute = Integer.valueOf(dateSec.substring(15, 17));

      TimeSlot slot = null;
      if (textSec.startsWith("G")) {
        String idString = textSec.split("#")[1];
        idString = idString.split(" ")[0];
        int id = Integer.valueOf(idString);
        slot = new TimeSlot(year, month, day, hour, minute, id, SlotType.CHANGE);
      } else if (textSec.startsWith("f")) {
        slot = new TimeSlot(year, month, day, hour, minute, -1, SlotType.SLEEP);
      } else if (textSec.startsWith("w")) {
        slot = new TimeSlot(year, month, day, hour, minute, -1, SlotType.WAKE);
      }

      slots.add(slot);
    }

    Collections.sort(slots, new SlotComparator());

    List<Shift> shifts = new ArrayList<>();
    Shift shift = null;
    int lastMinute = 0;
    SlotType lastType = null;
    for (TimeSlot ts : slots) {
      switch (ts.type) {
        case CHANGE:
          if (shift != null) {
            for (int i = lastMinute; i < 60; i++) {
              shift.asleep.put(i, lastType == SlotType.SLEEP);
            }
            shifts.add(shift);
          }
          shift = new Shift(ts.id);
          lastMinute = 0;
          lastType = SlotType.CHANGE;
          break;
        case SLEEP:
          if (shift != null) {
            for (int i = lastMinute; i < ts.minute; i++) {
              shift.asleep.put(i, false);
            }
          }
          lastType = SlotType.SLEEP;
          lastMinute = ts.minute;
          break;
        case WAKE:
          if (shift != null) {
            for (int i = lastMinute; i < ts.minute; i++) {
              shift.asleep.put(i, true);
            }
          }
          lastType = SlotType.WAKE;
          lastMinute = ts.minute;
          break;
      }
    }

    Map<Integer, Map<Integer, Integer>> guards = new HashMap<>();

    for (Shift s : shifts) {
      if (!guards.keySet().contains(s.id)) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < 60; i++) {
          map.put(i, 0);
        }
        guards.put(s.id, map);
      }

      for (Integer i : s.asleep.keySet()) {
        if (s.asleep.get(i)) {
          int val = guards.get(s.id).get(i);
          val++;
          guards.get(s.id).put(i, val);
        }
      }
    }

    String[] ret = {String.valueOf(getVal(guards))};
    return ret;
  }

  int getVal(Map<Integer, Map<Integer, Integer>> guards) {
    int maxTimeAsleep = -1;
    int minute = -1;
    int id = -1;

    for (Integer i : guards.keySet()) {
      for (Integer j : guards.get(i).keySet()) {
        if(guards.get(i).get(j) > maxTimeAsleep) {
          maxTimeAsleep = guards.get(i).get(j);
          minute = j;
          id = i;
        }
      }
    }

    return id * minute;
  }

  class Shift {

    int id;
    Map<Integer, Boolean> asleep = new HashMap<>();

    Shift(int id) {
      this.id = id;
    }
  }

  enum SlotType {
    CHANGE,
    SLEEP,
    WAKE
  }

  class TimeSlot {

    int year;
    int month;
    int day;
    int hour;
    int minute;
    int id;
    SlotType type;

    public TimeSlot(int year, int month, int day, int hour, int minute, int id, SlotType type) {
      this.year = year;
      this.month = month;
      this.day = day;
      this.hour = hour;
      this.minute = minute;
      this.id = id;
      this.type = type;
    }
  }

  class SlotComparator implements Comparator<TimeSlot> {

    @Override
    public int compare(TimeSlot o1, TimeSlot o2) {
      if (o1.year < o2.year) {
        return -1;
      } else if (o1.year > o2.year) {
        return 1;
      }
      if (o1.month < o2.month) {
        return -1;
      } else if (o1.month > o2.month) {
        return 1;
      }
      if (o1.day < o2.day) {
        return -1;
      } else if (o1.day > o2.day) {
        return 1;
      }
      if (o1.hour < o2.hour) {
        return -1;
      } else if (o1.hour > o2.hour) {
        return 1;
      }
      if (o1.minute < o2.minute) {
        return -1;
      } else if (o1.minute > o2.minute) {
        return 1;
      }
      return 0;
    }
  }
}
