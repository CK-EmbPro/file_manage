import type { Config } from "tailwindcss";

const config: Config = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        background: "var(--background)",
        foreground: "var(--foreground)", headerBgColor: "#F6F6F6",
        headerLinkBorderColor: "#DEE2E6",
        headerInfoBgColor: "#F3525A",
        welcomeBgColor: "#6B6A75",
        textColor: "#152440",
        inputBorderColor: "#CED4DA",
        navBarLinksColor: "#152440",
        dragAndDropColor: "#5A5A5A"
      },
    },
  },
  plugins: [],
};
export default config;
