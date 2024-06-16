import { Button } from "@/components/ui/button";
import Link from "next/link";

export default async function DashboardPage() {
    return (
        <>
            <p>onboarding</p>
            <Link href="/home">
                <Button>Go to home</Button>
            </Link>
        </>
    );
}
