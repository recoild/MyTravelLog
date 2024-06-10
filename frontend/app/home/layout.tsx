import { Button } from "@/components/ui/button";
import Link from "next/link";
import FeedHome from "./page";

export default async function DashboardLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <div>
            <h1>Home</h1>
            <p>Welcome</p>
            <Link href="/">
                <Button>Go back</Button>
            </Link>
            <Link href="/home">
                <Button>Go to Feed</Button>
            </Link>
            <Link href="/home/squad">
                <Button>Go to Squad</Button>
            </Link>
            {children}
        </div>
    );
}
