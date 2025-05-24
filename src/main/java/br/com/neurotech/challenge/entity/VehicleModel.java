package br.com.neurotech.challenge.entity;

public enum VehicleModel {
  HATCH, SUV;

  public static VehicleModel fromString(String model) {
    try {
      return VehicleModel.valueOf(model.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid vehicle model: " + model);
    }
  }
}
