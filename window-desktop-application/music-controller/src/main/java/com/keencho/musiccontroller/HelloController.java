package com.keencho.musiccontroller;

import com.sun.jna.Library;
import com.sun.jna.Native;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.util.Optional;

public class HelloController {

    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class);

        int MessageBoxA(int hWnd, String lpText, String lpCaption, int uType);
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Linker linker = Linker.nativeLinker();

        SymbolLookup linkerLookup = linker.defaultLookup();
        SymbolLookup systemLookup = SymbolLookup.loaderLookup();

        SymbolLookup symbolLookup = name -> systemLookup.lookup(name).or(() -> linkerLookup.lookup(name));

        Optional<MemorySegment> printfMemorySegment = symbolLookup.lookup("printf");
    }

//    @FXML
//    protected void onHelloButtonClick() {
//        WinDef.HWND hwnd = null;
//        while ( true ) {
//            hwnd = User32.INSTANCE.FindWindowEx(null, hwnd, null, null);
//
//            if (hwnd == null) {
//                break;
//            }
//
//            var buffer = new char[1024];
//            User32.INSTANCE.GetWindowText(hwnd, buffer, buffer.length);
//            var title = Native.toString(buffer);
//
//            if (title.toUpperCase().contains("YouTube Music".toUpperCase())) {
//
//                User32.INSTANCE.ShowWindow(hwnd, 9);
//
//                User32.INSTANCE.PostMessage(hwnd, WinUser.WM_KEYDOWN, new WinDef.WPARAM(0x20), new WinDef.LPARAM(0));
//                User32.INSTANCE.PostMessage(hwnd, WinUser.WM_KEYDOWN, new WinDef.WPARAM(0x20), new WinDef.LPARAM(1));

//                var input = new WinUser.INPUT();
//                input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
//                input.input.ki.wVk = new WinDef.WORD(KeyEvent.VK_SPACE);
//                input.input.ki.dwFlags = new WinDef.DWORD(WinUser.KEYBDINPUT.KEYEVENTF_EXTENDEDKEY);
//
//                var res = User32.INSTANCE.SendInput(new WinDef.DWORD(1), new WinUser.INPUT[] { input }, input.size());
//
//                System.out.println(res);

//                break;
//            }
//        }
//
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}