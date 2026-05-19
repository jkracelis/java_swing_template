package org.example.view.config;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

public final class ViewTheme {
  public static final Color PRIMARY = new Color(37, 99, 235);
  public static final Color SECONDARY = new Color(22, 163, 74);
  public static final Color MUTED = new Color(100, 116, 139);
  public static final Color ACCENT = new Color(219, 234, 254);
  public static final Color DESTRUCTIVE = new Color(220, 38, 38);
  public static final Color SURFACE = Color.WHITE;
  public static final Color SURFACE_SOFT = new Color(248, 250, 252);
  public static final Color TEXT_ON_COLOR = Color.WHITE;
  public static final Color TEXT_ON_COLOR_MUTED = new Color(235, 245, 255);
  public static final Color NOTIFICATION_BORDER = new Color(255, 255, 255, 80);

  public static final int SPACE_1 = 4;
  public static final int SPACE_2 = 8;
  public static final int SPACE_3 = 12;
  public static final int SPACE_4 = 16;
  public static final int SPACE_6 = 24;

  public static final Dimension NOTIFICATION_SIZE = new Dimension(260, 112);
  public static final EmptyBorder NOTIFICATION_PADDING =
      padding(SPACE_3, SPACE_4, SPACE_3, SPACE_4);
  public static final int NOTIFICATION_DURATION_SECONDS = 3;

  private ViewTheme() {}

  public static EmptyBorder padding(int all) {
    return new EmptyBorder(all, all, all, all);
  }

  public static EmptyBorder padding(int top, int left, int bottom, int right) {
    return new EmptyBorder(top, left, bottom, right);
  }

  public static Insets inset(int all) {
    return new Insets(all, all, all, all);
  }
}
