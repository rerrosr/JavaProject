package com.example.univerproject.aop;

import org.aspectj.lang.annotation.Pointcut;

/** The type Point cuts. */
public class PointCuts {
  /** All methods. */
  @Pointcut(value = "execution(* com.example.univerproject.service.*.*(..)) ")
  public void allMethods() {}
}
