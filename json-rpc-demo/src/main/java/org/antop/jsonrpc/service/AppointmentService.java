package org.antop.jsonrpc.service;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import org.antop.jsonrpc.exception.SlotNotAvailableException;
import org.antop.jsonrpc.model.Appointment;
import org.antop.jsonrpc.model.Patient;
import org.antop.jsonrpc.model.Slot;

import java.time.LocalDate;
import java.util.List;

@JsonRpcService("/appointmentService")
public interface AppointmentService {

    /**
     * 진료 가능한 시간대 조회
     *
     * @param date   날짜
     * @param doctor 의사 아이디
     * @return 진료 가능한 시간 목록
     */
    List<Slot> openSlotRequest(@JsonRpcParam(value = "date") LocalDate date, @JsonRpcParam(value = "doctor") String doctor);

    /**
     * 진료 예약
     *
     * @param slot    진료 시간
     * @param patient 환자 아이디
     * @return 예약 내역
     * @throws SlotNotAvailableException 예약 불가능 예외
     */
    @JsonRpcErrors({
            @JsonRpcError(exception = SlotNotAvailableException.class, code = 404, message = "Slot not available")
    })
    Appointment appointmentRequest(@JsonRpcParam(value = "slot") Slot slot, @JsonRpcParam(value = "patient") Patient patient) throws SlotNotAvailableException;

}
