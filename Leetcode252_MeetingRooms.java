package com.company.Leetcode_Complete_Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leetcode252_MeetingRooms {
    public static void main(String[] args) {
        int[][] inputArr = { {5,10}, {0,30}, {15,20}};

        boolean result = canAttend(inputArr);
        System.out.println(result);

    }

    private static boolean canAttend(int[][] inputArr) {
        List<Meeting> meetingList = new ArrayList<Meeting>();

        for(int i = 0; i < inputArr.length; i++){
            int[] temp = inputArr[i];
            Meeting meeting = new Meeting(temp[0], temp[1]);
            meetingList.add(meeting);
        }

        // sort based on ascending order of start time
        Collections.sort(meetingList, new TimeComparator());

        // print the list
        for(int i = 0; i < meetingList.size(); i++){
            Meeting temp = meetingList.get(i);
            System.out.println(temp.startTime + " " + temp.endTime);
        }

        // check consecutive meetings
        for(int i = 0; i < meetingList.size() - 1; i++){
            Meeting first = meetingList.get(i);
            Meeting second = meetingList.get(i + 1);

            // end time of 1st meeting, start time of 2nd meeting
            // if first meeting ends after the start time of the next meeting
            if(first.endTime < second.startTime){
                return false;
            }
        }

        return true;
    }
}

class TimeComparator implements Comparator<Meeting>{
    @Override
    public int compare(Meeting o1, Meeting o2) {
        return o1.startTime - o2.startTime;
    }
}

class Meeting {
    int startTime;
    int endTime;

    public Meeting(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
