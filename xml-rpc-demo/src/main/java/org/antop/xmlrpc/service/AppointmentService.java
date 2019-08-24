package org.antop.xmlrpc.service;


import org.antop.xmlrpc.model.Appointment;
import org.antop.xmlrpc.model.Patient;
import org.antop.xmlrpc.model.Slot;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    /**
     * 진료 가능한 시간대 조회
     *
     * @param date   날짜
     * @param doctor 의사 아이디
     * @return 진료 가능한 시간 목록
     */
    List<Slot> openSlotRequest(LocalDate date, String doctor);

    /**
     * 진료 예약
     *
     * @param slot    진료 시간
     * @param patient 환자 아이디
     * @return 예약 내역
     */
    Appointment appointmentRequest(Slot slot, Patient patient);

}
