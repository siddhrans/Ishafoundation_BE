package com.isha.donation.DAO;

import java.util.Map;
import org.springframework.stereotype.Repository;
import com.isha.donation.entity.Donar;

@Repository
public class DonarDAO {

    private static Map<Long, Donar> donarsMap;
}
