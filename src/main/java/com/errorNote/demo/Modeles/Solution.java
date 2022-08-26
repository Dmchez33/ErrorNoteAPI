package com.errorNote.demo.Modeles;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idS;
}
