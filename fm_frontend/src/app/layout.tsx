import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";
import {Open_Sans} from "next/font/google"

const open_sans = Open_Sans({
  display: "swap",
  subsets: ['latin'],
  variable: "--font-open-sans",
  weight: "300",
});
export const metadata: Metadata = {
  title: "Fm_manage",
  description: "File management app",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${open_sans.className} border border-black h-[100vh]`}
      >
        {children}
      </body>
    </html>
  );
}
