/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ensisa.table.server;

import com.ensisa.table.client.Personne;
import com.ensisa.table.client.Auditeur;
import com.ensisa.table.client.Schedule;
import com.ensisa.table.client.AuditionCalendarService;
import com.ensisa.table.client.Candidat;
import com.ensisa.table.client.TimeSlot;
import com.google.gwt.user.server.rpc.HybridServiceServlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The implemenation of the RPC service which runs on the server.
 */
public class AuditionCalendarServiceImpl extends HybridServiceServlet implements
    AuditionCalendarService {

  private static final String[] FIRST_NAMES = new String[] {
      "Inman", "Sally", "Omar", "Teddy", "Jimmy", "Cathy", "Barney", "Fred",
      "Eddie", "Carlos"};

  private static final String[] LAST_NAMES = new String[] {
      "Smith", "Jones", "Epps", "Gibbs", "Webber", "Blum", "Mendez",
      "Crutcher", "Needler", "Wilson", "Chase", "Edelstein"};

  private static final String[] SUBJECTS = new String[] {
      "Chemistry", "Phrenology", "Geometry", "Underwater Basket Weaving",
      "Basketball", "Computer Science", "Statistics", "Materials Engineering",
      "English Literature", "Geology"};

  private static final Personne[] NO_PEOPLE = new Personne[0];

  private static final int CLASS_LENGTH_MINS = 50;

  private static final int MAX_SCHED_ENTRIES = 5;

  private static final int MIN_SCHED_ENTRIES = 1;

  private static final int MAX_PEOPLE = 100;

  private static final int STUDENTS_PER_PROF = 5;

  private final List<Personne> people = new ArrayList<Personne>();

  private final Random rnd = new Random(3);

  public AuditionCalendarServiceImpl() {
    generateRandomPeople();
  }

  public Personne[] getPeople(int startIndex, int maxCount) {
    int peopleCount = people.size();

    int start = startIndex;
    if (start >= peopleCount) {
      return NO_PEOPLE;
    }

    int end = Math.min(startIndex + maxCount, peopleCount);
    if (start == end) {
      return NO_PEOPLE;
    }

    int resultCount = end - start;
    Personne[] results = new Personne[resultCount];
    for (int from = start, to = 0; to < resultCount; ++from, ++to) {
      results[to] = people.get(from);
    }

    return results;
  }

  /**
   * Write the serialized response out to stdout. This is a very unusual thing
   * to do, but it allows us to create a static file version of the response
   * without deploying a servlet.
   */
  @Override
  protected void onAfterResponseSerialized(String serializedResponse) {
    System.out.println(serializedResponse);
  }

  private void generateRandomPeople() {
    for (int i = 0; i < MAX_PEOPLE; ++i) {
      Personne personne = generateRandomPerson();
      people.add(personne);
    }
  }

  private Personne generateRandomPerson() {
    // 1 out of every so many people is a prof.
    //
    if (rnd.nextInt(STUDENTS_PER_PROF) == 1) {
      return generateRandomProfessor();
    } else {
      return generateRandomStudent();
    }
  }

  private Personne generateRandomProfessor() {
    Auditeur prof = new Auditeur();

    String firstName = pickRandomString(FIRST_NAMES);
    String lastName = pickRandomString(LAST_NAMES);
    prof.setName("Dr. " + firstName + " " + lastName);

    String subject = pickRandomString(SUBJECTS);
    prof.setDescription("Auditeur of " + subject);

    generateRandomSchedule(prof.getTeachingSchedule());

    return prof;
  }

  private void generateRandomSchedule(Schedule sched) {
    int range = MAX_SCHED_ENTRIES - MIN_SCHED_ENTRIES + 1;
    int howMany = MIN_SCHED_ENTRIES + rnd.nextInt(range);

    TimeSlot[] timeSlots = new TimeSlot[howMany];

    for (int i = 0; i < howMany; ++i) {
      int startHrs = 8 + rnd.nextInt(9); // 8 am - 5 pm
      int startMins = 15 * rnd.nextInt(4); // on the hour or some quarter
      int dayOfWeek = 1 + rnd.nextInt(5); // Mon - Fri

      int absStartMins = 60 * startHrs + startMins; // convert to minutes
      int absStopMins = absStartMins + CLASS_LENGTH_MINS;

      timeSlots[i] = new TimeSlot(dayOfWeek, absStartMins, absStopMins);
    }

    Arrays.sort(timeSlots);

    for (int i = 0; i < howMany; ++i) {
      sched.addTimeSlot(timeSlots[i]);
    }
  }

  private Personne generateRandomStudent() {
    Candidat candidat = new Candidat();

    String firstName = pickRandomString(FIRST_NAMES);
    String lastName = pickRandomString(LAST_NAMES);
    candidat.setName(firstName + " " + lastName);

    String subject = pickRandomString(SUBJECTS);
    candidat.setDescription("Majoring in " + subject);

    generateRandomSchedule(candidat.getClassSchedule());

    return candidat;
  }

  private String pickRandomString(String[] a) {
    int i = rnd.nextInt(a.length);
    return a[i];
  }
}
